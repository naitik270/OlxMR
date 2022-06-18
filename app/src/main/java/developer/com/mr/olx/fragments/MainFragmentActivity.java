package developer.com.mr.olx.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import developer.com.mr.olx.ApiInterface.InterfaceNearLocationMast;
import developer.com.mr.olx.R;
import developer.com.mr.olx.adapter.NearLocationListPaginationAdapter;
import developer.com.mr.olx.clsApiClasses.ClsNearLocationMasterList;
import developer.com.mr.olx.clsApiClasses.ClsNearLocationParams;
import developer.com.mr.olx.globalApi.ApiClient;
import developer.com.mr.olx.utils.PaginationScrollListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragmentActivity extends AppCompatActivity {

    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;
    public Snackbar snackbar;
    private boolean internetConnected = true;
    private CoordinatorLayout coordinatorLayout;

    private static final String TAG = "MainActivity";

    NearLocationListPaginationAdapter adapter;
    LinearLayoutManager ll_manager;

    RecyclerView rv_near_by_products;
    ProgressBar progressBar;

    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 0;
    private int currentPage = PAGE_START;
    private InterfaceNearLocationMast movieService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home_new);
        main();
    }

    private void main() {
        rv_near_by_products = (RecyclerView)findViewById(R.id.rv_near_by_products);
        progressBar = (ProgressBar) findViewById(R.id.ProgressBar);
        adapter = new NearLocationListPaginationAdapter(this);
        callAdapter();
    }

    void callAdapter() {
        rv_near_by_products.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        rv_near_by_products.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        ll_manager = new GridLayoutManager(this, 2);
        rv_near_by_products.setLayoutManager(ll_manager);
        rv_near_by_products.setItemAnimator(new DefaultItemAnimator());
        rv_near_by_products.setAdapter(adapter);

//        make smooth scroll
        rv_near_by_products.addOnScrollListener(new PaginationScrollListener(ll_manager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNextPage();
                    }
                }, 1000);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
        movieService = ApiClient.getRetrofitInstanceNewAPI().create(InterfaceNearLocationMast.class);
        loadFirstPage();
    }

    private void loadFirstPage() {
        Log.d("--API--", "loadFirstPage");
        callTopRatedMoviesApi().enqueue(new Callback<ClsNearLocationParams>() {
            @Override
            public void onResponse(Call<ClsNearLocationParams> call, Response<ClsNearLocationParams> response) {
                // Got data. Send it to adapter
                List<ClsNearLocationMasterList> results = fetchResults(response);
                progressBar.setVisibility(View.GONE);
                TOTAL_PAGES = response.body().getTotalPages();
                adapter.addAll(results);
                if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<ClsNearLocationParams> call, Throwable t) {
                t.printStackTrace();
                // TODO: 08/11/16 handle failure
            }
        });
    }

    private List<ClsNearLocationMasterList> fetchResults(Response<ClsNearLocationParams> response) {
        ClsNearLocationParams topRatedMovies = response.body();
        return topRatedMovies.getData();
    }

    private void loadNextPage() {
        Log.d("--API--", "loadNextPage");

        callTopRatedMoviesApi().enqueue(new Callback<ClsNearLocationParams>() {
            @Override
            public void onResponse(Call<ClsNearLocationParams> call, Response<ClsNearLocationParams> response) {
                adapter.removeLoadingFooter();
                isLoading = false;
                List<ClsNearLocationMasterList> results = fetchResults(response);
                adapter.addAll(results);
                if (currentPage != TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<ClsNearLocationParams> call, Throwable t) {
                t.printStackTrace();
                // TODO: 08/11/16 handle failure
            }
        });
    }

    private Call<ClsNearLocationParams> callTopRatedMoviesApi() {
        ClsNearLocationParams obj = new ClsNearLocationParams();
        obj.setLatitude(20.621813);
        obj.setLongitude(78.946523);
        obj.setFrom(0);
        obj.setTo(1000);
        obj.setLimit(10);
        obj.setPage_no(currentPage);
        Call<ClsNearLocationParams> call = movieService.getNearLocationListPost(obj);
        Log.e("--repo--", "URL: " + call.request().url());
        return call;
    }

}
