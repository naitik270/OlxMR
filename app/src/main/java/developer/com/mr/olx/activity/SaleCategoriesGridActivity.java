package developer.com.mr.olx.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import developer.com.mr.olx.ApiInterface.InterfaceCategories;
import developer.com.mr.olx.R;
import developer.com.mr.olx.adapter.CourseGVAdapter;
import developer.com.mr.olx.adapter.SaleCategoriesAdapter;
import developer.com.mr.olx.clicklistener.SaleCategoryClickListener;
import developer.com.mr.olx.clsApiClasses.ClsCategory;
import developer.com.mr.olx.clsApiClasses.Results;
import developer.com.mr.olx.clsApiClasses.clsAllCategory;
import developer.com.mr.olx.global.ConnectionDetector;
import developer.com.mr.olx.globalApi.ApiClient;
import developer.com.mr.olx.globalApi.SaleViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaleCategoriesGridActivity extends AppCompatActivity {


    private GridView idGVcourses;
    ImageView iv_back;
    private SaleViewModel saleViewModel;
    List<ClsCategory> lstCategories = new ArrayList<>();
    SaleCategoriesAdapter adapter;
    private ProgressDialog pd;
    Boolean isInternetPresent = false;
    ConnectionDetector cd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories_grid);

        saleViewModel = new ViewModelProvider(this).get(SaleViewModel.class);
        pd = new ProgressDialog(this);
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();

        idGVcourses = findViewById(R.id.idGVcourses);
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        adapter = new SaleCategoriesAdapter(SaleCategoriesGridActivity.this);
        pd.setMessage("Get All Category...!");

        if (isInternetPresent) {
            try {
                callSaleCategory();
                idGVcourses.setAdapter(adapter);
                adapter.SetOnItemClickListener((obj, position) ->
                        startActivity(new Intent(getApplicationContext(), SubCategoriesActivity.class)
                                .putExtra("cat_id", obj.getId())
                                .putExtra("mode", "Sale")
                                .putExtra("title_name", obj.getCatName())));
            } catch (Exception e) {
                pd.dismiss();
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show();
        }
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
                    adapter.addItemList(lstCategories);
                }
            }
            pd.dismiss();
        });
    }

}
