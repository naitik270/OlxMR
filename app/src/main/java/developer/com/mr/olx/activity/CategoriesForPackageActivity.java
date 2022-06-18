package developer.com.mr.olx.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import developer.com.mr.olx.R;
import developer.com.mr.olx.adapter.CategoriesBuyPackageAdapter;
import developer.com.mr.olx.adapter.SubCategoriesAdapter;
import developer.com.mr.olx.clicklistener.CatBuyPackageListener;
import developer.com.mr.olx.clsApiClasses.ClsCategory;
import developer.com.mr.olx.global.ConnectionDetector;
import developer.com.mr.olx.globalApi.SaleViewModel;

public class CategoriesForPackageActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    TextView txt_title_name;
    CategoriesBuyPackageAdapter mAdapter;
    ImageView iv_back;
    private SaleViewModel saleViewModel;
    private ProgressDialog pd;
    Boolean isInternetPresent = false;
    ConnectionDetector cd;
    List<ClsCategory> lstCategories = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_categories_activity);
        saleViewModel = new ViewModelProvider(this).get(SaleViewModel.class);
        pd = new ProgressDialog(this);
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();

        iv_back = findViewById(R.id.iv_back);
        txt_title_name = findViewById(R.id.txt_title_name);
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recyclerview.addItemDecoration(new DividerItemDecoration(recyclerview.getContext(),
                DividerItemDecoration.VERTICAL));
        txt_title_name.setText("Categories");
        iv_back.setOnClickListener(view -> finish());

        mAdapter = new CategoriesBuyPackageAdapter(CategoriesForPackageActivity.this);
        pd.setMessage("Get sub categories display...!");

        if (isInternetPresent) {
            try {
                callSaleCategory();
                recyclerview.setAdapter(mAdapter);
                adapterClick();
            } catch (Exception e) {
                pd.dismiss();
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show();
        }
    }

    void adapterClick() {

        mAdapter.SetOnClickListener(new CatBuyPackageListener() {
            @Override
            public void onItemClick(ClsCategory mClsSubCategories) {
                startActivity(new Intent(getApplicationContext(), ChoosePackageOptionActivity.class)
                        .putExtra("cat_name", mClsSubCategories.getCatName())
                        .putExtra("cat_id", mClsSubCategories.getId()));
            }
        });
    }

    void callSaleCategory() {
        pd.show();
        saleViewModel.getCategoriesAPIList().observe(this, lst -> {
            if (lst.getSuccess() != 1) {
                Toast.makeText(getApplicationContext(), "No Categories Founds...!", Toast.LENGTH_SHORT).show();
            } else {
                Log.d("--Cat--", "callSaleCategory: " + lstCategories.size());
                lstCategories = lst.getData();
                if (lstCategories.size() > 0) {
                    lstCategories.remove(0);
                    mAdapter.AddItems(lstCategories);
                }
            }
            pd.dismiss();
        });
    }

}
