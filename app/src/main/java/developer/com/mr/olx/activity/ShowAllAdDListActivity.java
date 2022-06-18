package developer.com.mr.olx.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import developer.com.mr.olx.ApiInterface.InterfaceShowAllAdsData;
import developer.com.mr.olx.R;
import developer.com.mr.olx.adapter.ShowAllAdsPaginationAdapter;
import developer.com.mr.olx.clsApiClasses.ClsShowCategoryDataParams;
import developer.com.mr.olx.clsApiClasses.ClsShowCategoryDataResponseList;
import developer.com.mr.olx.global.ClsGlobal;
import developer.com.mr.olx.global.ClsPreferencesInfo;
import developer.com.mr.olx.globalApi.ApiClient;
import developer.com.mr.olx.utils.PaginationScrollListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowAllAdDListActivity extends AppCompatActivity {

    int cat_id = 0;
    int sub_id = 0;
    List<Integer> sub_id_list = new ArrayList<>();
    List<Integer> brand_id_list = new ArrayList<>();
    ClsPreferencesInfo obj;
    double get_latitude = 0.0, get_longitude = 0.0;
    String get_location = "";
    String sub_title = "";
    String sub_id_list_val = "";
    String brand_id_list_val = "";
    TextView txt_title, txt_total_ads_count;
    private InterfaceShowAllAdsData mInterfaceShowAllAdsData;

    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 0;
    private int currentPage = PAGE_START;
    RecyclerView rv_show_all_ads;
    LinearLayoutManager linearLayoutManager;
    ShowAllAdsPaginationAdapter mAdapter;
    private ProgressBar progressBar;
    int total_results = 0;
    int total_pages = 0;
    NestedScrollView nested_scroll_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all_ads_list_activity);

        obj = new ClsPreferencesInfo();
        obj = ClsGlobal.getPreferencesInfo(ShowAllAdDListActivity.this);

        get_latitude = obj.getCurrentLatitude();
        get_longitude = obj.getCurrentLongitude();
        get_location = obj.getCurrentLocation();

        Log.d("--FillAPI--", "UserCode: " + new Gson().toJson(obj));
        sub_title = getIntent().getStringExtra("sub_title");
        cat_id = getIntent().getIntExtra("cat_id", 0);
        sub_id = getIntent().getIntExtra("sub_id", 0);

        txt_title = findViewById(R.id.txt_title);
        txt_total_ads_count = findViewById(R.id.txt_total_ads_count);
        nested_scroll_view = findViewById(R.id.nested_scroll_view);
        rv_show_all_ads = findViewById(R.id.rv_show_all_ads);
        txt_title.setText(sub_title);
        progressBar = findViewById(R.id.ProgressBar);
        mAdapter = new ShowAllAdsPaginationAdapter(this);

        sub_id_list = (ArrayList<Integer>) getIntent().getSerializableExtra("sub_id_list");
        Log.d("--pass--", "AllProduct: " + new Gson().toJson(sub_id_list).replace("[", "").replace("]", ""));
        sub_id_list_val = new Gson().toJson(sub_id_list).replace("[", "").replace("]", "");
        Log.d("--pass--", "sub_id_list_val: " + sub_id_list_val);

        brand_id_list = (ArrayList<Integer>) getIntent().getSerializableExtra("brand_id_list");
        Log.d("--pass--", "AllProduct: " + new Gson().toJson(brand_id_list).replace("[", "").replace("]", ""));
        brand_id_list_val = new Gson().toJson(brand_id_list).replace("[", "").replace("]", "");
        Log.d("--pass--", "brand_id_list_val: " + brand_id_list_val);
        callAdapter();
    }


    void callAdapter() {

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_show_all_ads.setLayoutManager(linearLayoutManager);
        rv_show_all_ads.setItemAnimator(new DefaultItemAnimator());
        rv_show_all_ads.setAdapter(mAdapter);
        rv_show_all_ads.setNestedScrollingEnabled(false);

        nested_scroll_view.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                View view = (View) nested_scroll_view.getChildAt(nested_scroll_view.getChildCount() - 1);
                int diff = (view.getBottom() - (nested_scroll_view.getHeight() + nested_scroll_view.getScrollY()));

                if (TOTAL_PAGES > currentPage) {
                    Log.d("--Load--", "IFIFIFI_total_results");
                    if (diff == 0) {
                        // your pagination code
                        isLoading = true;
                        currentPage += 1;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadNextPage();
                            }
                        }, 1000);
                        return;
                    }
                } else {
                    Log.d("--Load--", "else_total_results");
                }
            }
        });

        mInterfaceShowAllAdsData = ApiClient.getRetrofitInstanceNewAPI().create(InterfaceShowAllAdsData.class);
        loadFirstPage();
    }

    private Call<ClsShowCategoryDataParams> callShowAllAdsDataApi() {
        ClsShowCategoryDataParams obj = new ClsShowCategoryDataParams();
        obj.setCat_id(cat_id);
        obj.setSub_cat_id(sub_id_list_val);
        obj.setLimit(10);
        obj.setPage_no(currentPage);
        obj.setLatitude(20.621813);
        obj.setLongitude(78.946523);
        obj.setPrice_from(0.0);
        obj.setPrice_to(0.0);
        obj.setBrand_id("");
        obj.setJob_salary_from(0.0);
        obj.setJob_salary_to(0.0);
        obj.setDistance_sorting("0");
        obj.setDate_sorting("0");
        obj.setPrice_sorting("ASC");
        Log.e("--repo--", "URL: " + mInterfaceShowAllAdsData.getShowCategoryDataListPost(obj).request().url());
        Log.d("--movie--", "obj: " + new Gson().toJson(obj));
        return mInterfaceShowAllAdsData.getShowCategoryDataListPost(obj);
    }

    private void loadFirstPage() {
        Log.d("--Load--", "loadFirstPage");
        callShowAllAdsDataApi().enqueue(new Callback<ClsShowCategoryDataParams>() {
            @Override
            public void onResponse(Call<ClsShowCategoryDataParams> call, Response<ClsShowCategoryDataParams> response) {
                // Got data. Send it to adapter
                List<ClsShowCategoryDataResponseList> results = fetchResults(response);
                progressBar.setVisibility(View.GONE);
                TOTAL_PAGES = response.body().getTotalPages();
                total_results = response.body().getTotalResults();
                txt_total_ads_count.setText(total_results + " ADs found");
                total_pages = response.body().getTotalPages();
                mAdapter.addAll(results);
                if (total_results > 10) {
                    if (currentPage <= TOTAL_PAGES)
                        mAdapter.addLoadingFooter();
                    else isLastPage = true;
                }
            }

            @Override
            public void onFailure(Call<ClsShowCategoryDataParams> call, Throwable t) {
                t.printStackTrace();
                // TODO: 08/11/16 handle failure
            }
        });
    }

    private void loadNextPage() {
        Log.d("--Load--", "loadNextPage");
        callShowAllAdsDataApi().enqueue(new Callback<ClsShowCategoryDataParams>() {
            @Override
            public void onResponse(Call<ClsShowCategoryDataParams> call, Response<ClsShowCategoryDataParams> response) {
                mAdapter.removeLoadingFooter();
                isLoading = false;
                List<ClsShowCategoryDataResponseList> results = fetchResults(response);
                mAdapter.addAll(results);
                if (currentPage != TOTAL_PAGES)
                    mAdapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<ClsShowCategoryDataParams> call, Throwable t) {
                t.printStackTrace();
                // TODO: 08/11/16 handle failure
            }
        });
    }

    private List<ClsShowCategoryDataResponseList> fetchResults(Response<ClsShowCategoryDataParams> response) {
        ClsShowCategoryDataParams topRatedMovies = response.body();
        return topRatedMovies.getData();
    }

}
