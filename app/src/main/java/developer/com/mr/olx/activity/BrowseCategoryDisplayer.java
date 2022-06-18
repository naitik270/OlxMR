package developer.com.mr.olx.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import developer.com.mr.olx.ApiCallInterfaces.CarApiClient;
import developer.com.mr.olx.CarResponseClasses.DataCarClasses;
import developer.com.mr.olx.CarResponseClasses.ResponseCarClasses;
import developer.com.mr.olx.Networks.Network;
import developer.com.mr.olx.R;
import developer.com.mr.olx.adapter.CarViewAdapter;
import developer.com.mr.olx.clicklistener.CarClickListener;
import developer.com.mr.olx.productsList.ProductListViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrowseCategoryDisplayer extends AppCompatActivity implements CarClickListener {


    private RecyclerView rv;
    private int position;
    private Animation topAnim, bottomAnim;
    private TextView tvCategoryName;
    private ProgressBar pb;
    ImageView iv_back;
    private List<DataCarClasses> dataCarClassesArrayList = new ArrayList<>();
    private CarViewAdapter carViewAdapter;
    ProductListViewModel productListViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_category_displayer);

        productListViewModel = new ViewModelProvider(this).get(ProductListViewModel.class);

        initViews();
        getIntents();

        checkPosition();
    }


    private void initViews() {
        iv_back = findViewById(R.id.iv_back);
        rv = findViewById(R.id.RVBC);
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        tvCategoryName = findViewById(R.id.tvCategoryNameBC);
        pb = findViewById(R.id.ProgressBarBC);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void getIntents() {
        if (getIntent() != null) {
            position = getIntent().getIntExtra("position", 0);
        }
    }


    private void checkPosition() {
        if (position == 1) {
            tvCategoryName.setText("cars");
            setCarRecyclerView();
            setCarAdapter();

            displayAllProducts();

        }

    }

    private void setCarAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        carViewAdapter = new CarViewAdapter(dataCarClassesArrayList, this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(carViewAdapter);
    }


    void displayAllProducts() {
        productListViewModel.getProfileList(100, "desc");
    }

    private void setCarRecyclerView() {
        CarApiClient apiClient = Network.getInstance().create(CarApiClient.class);
        apiClient.getApit().enqueue(new Callback<ResponseCarClasses>() {
            @Override
            public void onResponse(Call<ResponseCarClasses> call, Response<ResponseCarClasses> response) {
                if (response.body() != null) {
                    dataCarClassesArrayList = response.body().getData();
                    carViewAdapter.updateData(dataCarClassesArrayList);
                    pb.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseCarClasses> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(DataCarClasses dataCarClasses) {

    }
}
