package developer.com.mr.olx.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import developer.com.mr.olx.ApiInterface.InterfaceNearLocationMast;
import developer.com.mr.olx.R;
import developer.com.mr.olx.activity.BrowseCategoryDisplayer;
import developer.com.mr.olx.activity.GetLocationActivity;
import developer.com.mr.olx.activity.ItemDetails;
import developer.com.mr.olx.activity.ProfileActivity;
import developer.com.mr.olx.activity.SaleCategoriesGridActivity;
import developer.com.mr.olx.activity.SubCategoriesActivity;
import developer.com.mr.olx.activity.UserPagingActivity;
import developer.com.mr.olx.adapter.HeaderCategoriesAdapter;
import developer.com.mr.olx.adapter.NearLocationListPaginationAdapter;
import developer.com.mr.olx.adapter.SliderAdapterExample;
import developer.com.mr.olx.classes.DataClasses;
import developer.com.mr.olx.clicklistener.AllInOneClickListener;
import developer.com.mr.olx.clicklistener.HeaderCategoryClickListener;
import developer.com.mr.olx.clsApiClasses.ClsCategory;
import developer.com.mr.olx.clsApiClasses.ClsNearLocationMasterList;
import developer.com.mr.olx.clsApiClasses.ClsNearLocationParams;
import developer.com.mr.olx.global.ClsGlobal;
import developer.com.mr.olx.global.ClsPreferencesInfo;
import developer.com.mr.olx.global.ConnectionDetector;
import developer.com.mr.olx.globalApi.ApiClient;
import developer.com.mr.olx.globalApi.NearLocationListViewModel;
import developer.com.mr.olx.globalApi.SaleViewModel;
import developer.com.mr.olx.utils.PaginationScrollListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static developer.com.mr.olx.global.ClsGlobal.PosterListData;
import static developer.com.mr.olx.global.ClsGlobal.mPosterList;


public class HomeFragment extends Fragment implements HeaderCategoryClickListener, AllInOneClickListener {

    private RecyclerView rv_header_cat, rv_near_by_products;
    private NearLocationListPaginationAdapter mNearLocationListPaginationAdapter;
    private HeaderCategoriesAdapter mCategoriesAdapter;
    private ProgressBar progressBar;
    private ImageView iv_profile;

    SliderView imageSlider;
    private SliderAdapterExample mSliderAdapterExample;
    ImageView iv_msg;

    private SaleViewModel saleViewModel;
    private NearLocationListViewModel nearLocationListViewModel;
    private ProgressDialog pd;
    Boolean isInternetPresent = false;
    ConnectionDetector cd;

    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 0;
    private int currentPage = PAGE_START;
    int total_results = 0;

    TextView txt_current_location;
    ClsPreferencesInfo obj;
    private InterfaceNearLocationMast movieService;
    LinearLayoutManager ll_manager;
    NestedScrollView nested_scroll_view;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        saleViewModel = new ViewModelProvider(this).get(SaleViewModel.class);
        nearLocationListViewModel = new ViewModelProvider(this).get(NearLocationListViewModel.class);
        obj = new ClsPreferencesInfo();
        obj = ClsGlobal.getPreferencesInfo(requireActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        main(v);
        return v;
    }

    private void main(View view) {
        txt_current_location = view.findViewById(R.id.txt_current_location);
        nested_scroll_view = view.findViewById(R.id.nested_scroll_view);
        iv_msg = view.findViewById(R.id.iv_msg);
        rv_header_cat = view.findViewById(R.id.rv_header_cat);
        rv_header_cat.setHasFixedSize(true);
        rv_header_cat.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false));
        mCategoriesAdapter = new HeaderCategoriesAdapter(requireActivity());

        imageSlider = view.findViewById(R.id.imageSlider);
        rv_near_by_products = view.findViewById(R.id.rv_near_by_products);
        iv_profile = view.findViewById(R.id.iv_profile);
        progressBar = view.findViewById(R.id.ProgressBar);
        mNearLocationListPaginationAdapter = new NearLocationListPaginationAdapter(requireActivity());

        pd = new ProgressDialog(requireActivity());
        pd.setMessage("Get All Category...!");
        cd = new ConnectionDetector(requireActivity());
        isInternetPresent = cd.isConnectingToInternet();

        if (isInternetPresent) {
            try {
                callSaleCategory();
                rv_header_cat.setAdapter(mCategoriesAdapter);
                mCategoriesAdapter.SetOnItemClickListener((obj, position) -> {
                    if (obj.getId() == 3) {
                        startActivity(new Intent(requireActivity(), SaleCategoriesGridActivity.class));
                    } else {
                        startActivity(new Intent(requireActivity(), SubCategoriesActivity.class)
                                .putExtra("cat_id", obj.getId())
                                .putExtra("mode", "Purchase")
                                .putExtra("title_name", obj.getCatName()));
                    }
                });
            } catch (Exception e) {
                pd.dismiss();
                e.printStackTrace();
            }
        } else {
            Toast.makeText(requireActivity(), "No Internet", Toast.LENGTH_SHORT).show();
        }

//        setSliderAdapterView();

        iv_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), FilterActivity.class));
                startActivity(new Intent(getActivity(), MainFragmentActivity.class));
            }
        });

        iv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ProfileActivity.class));
            }
        });

        txt_current_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GetLocationActivity.class).putExtra("location_mode", "direct"));
            }
        });

        callAdapter();
    }


    void callAdapter() {
        rv_near_by_products.addItemDecoration(new DividerItemDecoration(requireActivity(),
                DividerItemDecoration.HORIZONTAL));
        rv_near_by_products.addItemDecoration(new DividerItemDecoration(requireActivity(),
                DividerItemDecoration.VERTICAL));
        ll_manager = new GridLayoutManager(requireActivity(), 2);
        rv_near_by_products.setLayoutManager(ll_manager);
        rv_near_by_products.setItemAnimator(new DefaultItemAnimator());
        rv_near_by_products.setAdapter(mNearLocationListPaginationAdapter);
        rv_near_by_products.setNestedScrollingEnabled(false);

        nested_scroll_view.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                View view = (View) nested_scroll_view.getChildAt(nested_scroll_view.getChildCount() - 1);

                int diff = (view.getBottom() - (nested_scroll_view.getHeight() + nested_scroll_view
                        .getScrollY()));

                if (TOTAL_PAGES > currentPage) {
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
                    Log.d("--Load--", "else");
                }
            }
        });

        /*rv_near_by_products.addOnScrollListener(new PaginationScrollListener(ll_manager) {
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
        });*/

        movieService = ApiClient.getRetrofitInstanceNewAPI().create(InterfaceNearLocationMast.class);
        loadFirstPage();
    }


    void setSliderAdapterView() {
        mPosterList = new ArrayList<>();
        mSliderAdapterExample = new SliderAdapterExample(getActivity());
        imageSlider.setSliderAdapter(mSliderAdapterExample);
        imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        imageSlider.setIndicatorSelectedColor(Color.WHITE);
        imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        imageSlider.setScrollTimeInSec(3);
        imageSlider.setAutoCycle(true);
        imageSlider.startAutoCycle();

        imageSlider.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + imageSlider.getCurrentPagePosition());
            }
        });
        PosterListData();
        mSliderAdapterExample.renewItems(mPosterList);
    }


    @Override
    public void onItemClick(DataClasses dataClasses) {
        try {
            Intent intent = new Intent(getContext(), ItemDetails.class);
            intent.putExtra("price", dataClasses.getPrice().getValue().getDisplay());
            intent.putExtra("title", dataClasses.getTitle());
            intent.putExtra("extras", dataClasses.getMainInfo());
            intent.putExtra("image1", dataClasses.getImages().get(0).getUrl());
            intent.putExtra("image2", dataClasses.getImages().get(1).getUrl());
            intent.putExtra("town", dataClasses.getLocationsResolved().getADMINLEVEL3Name());
            intent.putExtra("city", dataClasses.getLocationsResolved().getADMINLEVEL1Name());
            intent.putExtra("description", dataClasses.getDescription());
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Failed to fetch results, try again!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemClick(ClsCategory obj, int position) {

        if (position == 0) {
//            startActivity(new Intent(getContext(), AllCategoriesGridActivity.class));
            startActivity(new Intent(getContext(), UserPagingActivity.class));
        } else if (position == 1) {
            Intent intent = new Intent(getContext(), BrowseCategoryDisplayer.class);
            intent.putExtra("position", position);
            startActivity(intent);
        } else if (position == 3) {
            Intent intent = new Intent(getContext(), BrowseCategoryDisplayer.class);
            intent.putExtra("position", position);
            startActivity(intent);
        } else {
            Toast.makeText(getActivity(), "Under development...", Toast.LENGTH_SHORT).show();
        }

        /*mCategoriesAdapter.SetOnItemClickListener((obj, position) ->
                startActivity(new Intent(getApplicationContext(), SubCategoriesActivity.class)
                        .putExtra("position", position)
                        .putExtra("title_name", obj.getCatName())));
*/
    }

    void callSaleCategory() {
        pd.show();
        saleViewModel.getCategoriesAPIList().observe(requireActivity(), lst -> {
            if (lst.getSuccess() != 1) {
                Toast.makeText(requireActivity(), "No Categories Founds...!", Toast.LENGTH_SHORT).show();
            } else {
                mCategoriesAdapter.addItemList(lst.getData());
            }
            pd.dismiss();
        });
    }

    private void loadFirstPage() {
        Log.d("--Load--", "loadFirstPage");
        callTopRatedMoviesApi().enqueue(new Callback<ClsNearLocationParams>() {
            @Override
            public void onResponse(Call<ClsNearLocationParams> call, Response<ClsNearLocationParams> response) {
                // Got data. Send it to adapter
                List<ClsNearLocationMasterList> results = fetchResults(response);
                progressBar.setVisibility(View.GONE);
                TOTAL_PAGES = response.body().getTotalPages();
                mNearLocationListPaginationAdapter.addAll(results);
               /* if (total_results > 20) {
                    if (currentPage <= TOTAL_PAGES)
                        mNearLocationListPaginationAdapter.addLoadingFooter();
                    else isLastPage = true;
                }*/

                if (currentPage <= TOTAL_PAGES)
                    mNearLocationListPaginationAdapter.addLoadingFooter();
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

    private List<ClsNearLocationMasterList> fetchResults(Response<ClsNearLocationParams> response) {
        ClsNearLocationParams topRatedMovies = response.body();
        return topRatedMovies.getData();
    }

    private void loadNextPage() {
        Log.d("--Load--", "loadNextPage");
        callTopRatedMoviesApi().enqueue(new Callback<ClsNearLocationParams>() {
            @Override
            public void onResponse(Call<ClsNearLocationParams> call, Response<ClsNearLocationParams> response) {
                mNearLocationListPaginationAdapter.removeLoadingFooter();
                isLoading = false;
                List<ClsNearLocationMasterList> results = fetchResults(response);
                mNearLocationListPaginationAdapter.addAll(results);
                if (currentPage != TOTAL_PAGES)
                    mNearLocationListPaginationAdapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<ClsNearLocationParams> call, Throwable t) {
                t.printStackTrace();
                // TODO: 08/11/16 handle failure
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        obj = ClsGlobal.getPreferencesInfo(requireActivity());
        txt_current_location.setText(obj.getCurrentLocation());

    }
}