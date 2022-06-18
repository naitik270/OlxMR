package developer.com.mr.olx.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonUiContext;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import developer.com.mr.olx.R;
import developer.com.mr.olx.adapter.SubCategoriesAdapter;
import developer.com.mr.olx.clsApiClasses.ClsSubCategories;
import developer.com.mr.olx.global.ConnectionDetector;
import developer.com.mr.olx.globalApi.SaleViewModel;

public class SubCategoriesActivity extends AppCompatActivity {

    int cat_id = 0;
    RecyclerView recyclerview;
    TextView txt_title_name;
    SubCategoriesAdapter mAdapter;
    String title_name = "";
    ImageView iv_back;
    private SaleViewModel saleViewModel;
    private ProgressDialog pd;
    Boolean isInternetPresent = false;
    ConnectionDetector cd;
    String mode = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_categories_activity);
        saleViewModel = new ViewModelProvider(this).get(SaleViewModel.class);
        pd = new ProgressDialog(this);
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();

        cat_id = getIntent().getIntExtra("cat_id", 0);
        title_name = getIntent().getStringExtra("title_name");
        mode = getIntent().getStringExtra("mode");

        iv_back = findViewById(R.id.iv_back);
        txt_title_name = findViewById(R.id.txt_title_name);
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recyclerview.addItemDecoration(new DividerItemDecoration(recyclerview.getContext(),
                DividerItemDecoration.VERTICAL));
        txt_title_name.setText(title_name);
        iv_back.setOnClickListener(view -> finish());

        mAdapter = new SubCategoriesAdapter(SubCategoriesActivity.this);
        pd.setMessage("Get sub categories display...!");

        if (isInternetPresent) {
            try {
                callSubCategoryByIDAPI(cat_id);
                recyclerview.setAdapter(mAdapter);
                if (mode != null && mode.equalsIgnoreCase("Purchase")) {
//                    adapterPurchaseClick();
                    adapterShowAllAdsClick();
                } else {
                    adapterClick();
                }
            } catch (Exception e) {
                pd.dismiss();
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show();
        }
    }

    List<Integer> sub_id_list = new ArrayList<>();
    List<Integer> brand_id_list = new ArrayList<>();

    void adapterClick() {
        mAdapter.SetOnClickListener(obj -> {
            if (cat_id == 4) {
                if (obj.getSubCatName().contains("Mobiles") ||
                        obj.getSubCatName().contains("Tablet")) {
                    startActivity(new Intent(getApplicationContext(),
                            MobileFormActivity.class)
                            .putExtra("cat_id", cat_id)
                            .putExtra("sub_id", obj.getSubCatId())
                            .putExtra("sub_title", obj.getSubCatName()));
                } else {
                    startActivity(new Intent(getApplicationContext(),
                            AllProductForm.class)
                            .putExtra("cat_id", cat_id)
                            .putExtra("sub_id", obj.getSubCatId())
                            .putExtra("sub_title", obj.getSubCatName()));
                }
            } else if (cat_id == 5) {
                if (obj.getSubCatName().contains("Car sell")) {
                    startActivity(new Intent(getApplicationContext(),
                            CarSellFormActivity.class)
                            .putExtra("cat_id", cat_id)
                            .putExtra("sub_id", obj.getSubCatId())
                            .putExtra("sub_title", obj.getSubCatName()));
                } else if (obj.getSubCatName().contains("Car rent")) {
                    startActivity(new Intent(getApplicationContext(),
                            CarRentFormActivity.class)
                            .putExtra("cat_id", cat_id)
                            .putExtra("sub_id", obj.getSubCatId())
                            .putExtra("sub_title", obj.getSubCatName()));
                } else {
                    startActivity(new Intent(getApplicationContext(),
                            AllProductForm.class)
                            .putExtra("cat_id", cat_id)
                            .putExtra("sub_id", obj.getSubCatId())
                            .putExtra("sub_title", obj.getSubCatName()));
                }
            } else if (cat_id == 6) {
                if (obj.getSubCatName().contains("Sell Property")) {
                    startActivity(new Intent(getApplicationContext(),
                            PropertySellFormActivity.class)
                            .putExtra("cat_id", cat_id)
                            .putExtra("sub_id", obj.getSubCatId())
                            .putExtra("sub_title", obj.getSubCatName()));
                } else if (obj.getSubCatName().contains("Rent Property")) {
                    startActivity(new Intent(getApplicationContext(),
                            PropertyRentFormActivity.class)
                            .putExtra("cat_id", cat_id)
                            .putExtra("sub_id", obj.getSubCatId())
                            .putExtra("sub_title", obj.getSubCatName()));
                } else if (obj.getSubCatName().contains("Sell office") ||
                        obj.getSubCatName().contains("Rent office")) {
                    startActivity(new Intent(getApplicationContext(),
                            OfficeSellRentFormActivity.class)
                            .putExtra("cat_id", cat_id)
                            .putExtra("sub_id", obj.getSubCatId())
                            .putExtra("sub_title", obj.getSubCatName()));
                }
            } else if (cat_id == 7) {
                startActivity(new Intent(getApplicationContext(),
                        JobFormActivity.class)
                        .putExtra("cat_id", cat_id)
                        .putExtra("sub_id", obj.getSubCatId())
                        .putExtra("sub_title", obj.getSubCatName()));
            } else if (cat_id == 9) {
                if (obj.getSubCatName().contains("Bicycles") ||
                        obj.getSubCatName().contains("Spare parts")) {
                    startActivity(new Intent(getApplicationContext(),
                            AllProductForm.class)
                            .putExtra("cat_id", cat_id)
                            .putExtra("sub_id", obj.getSubCatId())
                            .putExtra("sub_title", obj.getSubCatName()));
                } else if (obj.getSubCatName().contains("Bikes") ||
                        obj.getSubCatName().contains("Scooters")) {
                    startActivity(new Intent(getApplicationContext(),
                            BikeScooterFormActivity.class)
                            .putExtra("cat_id", cat_id)
                            .putExtra("sub_id", obj.getSubCatId())
                            .putExtra("sub_title", obj.getSubCatName()));
                }
            } else if (cat_id == 11) {
                if (obj.getSubCatName().contains("Spare parts")) {
                    startActivity(new Intent(getApplicationContext(),
                            AllProductForm.class)
                            .putExtra("cat_id", cat_id)
                            .putExtra("sub_id", obj.getSubCatId())
                            .putExtra("sub_title", obj.getSubCatName()));
                } else if (obj.getSubCatName().contains("Commercial Vehicles")) {
                    startActivity(new Intent(getApplicationContext(),
                            CommercialVehicleFormActivity.class)
                            .putExtra("cat_id", cat_id)
                            .putExtra("sub_id", obj.getSubCatId())
                            .putExtra("sub_title", obj.getSubCatName()));
                }
            } else if (cat_id == 8 || cat_id == 10 || cat_id == 12 || cat_id == 13 || cat_id == 14 || cat_id == 15) {
                startActivity(new Intent(getApplicationContext(),
                        AllProductForm.class)
                        .putExtra("cat_id", cat_id)
                        .putExtra("sub_id", obj.getSubCatId())
                        .putExtra("sub_title", obj.getSubCatName()));
            }

        });
    }

    void adapterShowAllAdsClick() {
        mAdapter.SetOnClickListener(obj -> {
            sub_id_list = new ArrayList<>();
            brand_id_list = new ArrayList<>();
            if (obj.getSubCatName() != null) {
                if (obj.getSubCatName().equalsIgnoreCase("View All")) {
                    for (int i = 0; i < lstCategories.size(); i++) {
                        sub_id_list.add(lstCategories.get(i).getSubCatId());
                        brand_id_list.add(lstCategories.get(i).getSubCatId());
                        Log.d("--pass--", "getSubCatId: " + lstCategories.get(i).getSubCatId());
                    }
                    Log.d("--pass--", "getSubCatId: " + new Gson().toJson(sub_id_list));
                } else {
                    sub_id_list.add(obj.getSubCatId());
                }
                startActivity(new Intent(getApplicationContext(),
                        ShowAllAdDListActivity.class)
                        .putExtra("cat_id", cat_id)
                        .putExtra("sub_id", obj.getSubCatId())
                        .putExtra("sub_id_list", (Serializable) sub_id_list)
                        .putExtra("sub_title", obj.getSubCatName()));
            }
        });
    }

/*
    void adapterPurchaseClick() {
        mAdapter.SetOnClickListener(obj -> {
            test = new ArrayList<>();
            if (cat_id == 4) {
                if (obj.getSubCatName().contains("Mobiles") ||
                        obj.getSubCatName().contains("Tablet")) {
                    startActivity(new Intent(getApplicationContext(),
                            MobileFormActivity.class)
                            .putExtra("cat_id", cat_id)
                            .putExtra("sub_id", obj.getSubCatId())
                            .putExtra("sub_title", obj.getSubCatName()));
                } else if (obj.getSubCatName().equalsIgnoreCase("View All")) {
                    for (int i = 0; i < lstCategories.size(); i++) {
                        test.add(lstCategories.get(i).getSubCatId());
                        Log.d("--pass--", "getSubCatId: " + lstCategories.get(i).getSubCatId());
                    }
                    Log.d("--pass--", "getSubCatId: " + new Gson().toJson(test));
                    startActivity(new Intent(getApplicationContext(),
                            ShowAllAdDListActivity.class)
                            .putExtra("cat_id", cat_id)
                            .putExtra("sub_id", obj.getSubCatId())
                            .putExtra("sub_id_list", (Serializable) test)
                            .putExtra("sub_title", obj.getSubCatName()));

                } else {
                    startActivity(new Intent(getApplicationContext(),
                            AllProductForm.class)
                            .putExtra("cat_id", cat_id)
                            .putExtra("sub_id", obj.getSubCatId())
                            .putExtra("sub_title", obj.getSubCatName()));
                }
            } else if (cat_id == 5) {
                if (obj.getSubCatName().contains("Car sell")) {
                    startActivity(new Intent(getApplicationContext(),
                            CarSellFormActivity.class)
                            .putExtra("cat_id", cat_id)
                            .putExtra("sub_id", obj.getSubCatId())
                            .putExtra("sub_title", obj.getSubCatName()));
                } else if (obj.getSubCatName().contains("Car rent")) {
                    startActivity(new Intent(getApplicationContext(),
                            CarRentFormActivity.class)
                            .putExtra("cat_id", cat_id)
                            .putExtra("sub_id", obj.getSubCatId())
                            .putExtra("sub_title", obj.getSubCatName()));
                } else if (obj.getSubCatName().equalsIgnoreCase("View All")) {
                    for (int i = 0; i < lstCategories.size(); i++) {
                        test.add(lstCategories.get(i).getSubCatId());
                        Log.d("--pass--", "getSubCatId: " + lstCategories.get(i).getSubCatId());
                    }
                    Log.d("--pass--", "getSubCatId: " + new Gson().toJson(test));
                    Log.d("--pass--", "getSubCatId: " + new Gson().toJson(test).replace("[", "").replace("]", ""));

                } else {
                    startActivity(new Intent(getApplicationContext(),
                            AllProductForm.class)
                            .putExtra("cat_id", cat_id)
                            .putExtra("sub_id", obj.getSubCatId())
                            .putExtra("sub_title", obj.getSubCatName()));
                }
            } else if (cat_id == 6) {
                if (obj.getSubCatName().contains("Sell Property")) {
                    startActivity(new Intent(getApplicationContext(),
                            PropertySellFormActivity.class)
                            .putExtra("cat_id", cat_id)
                            .putExtra("sub_id", obj.getSubCatId())
                            .putExtra("sub_title", obj.getSubCatName()));
                } else if (obj.getSubCatName().contains("Rent Property")) {
                    startActivity(new Intent(getApplicationContext(),
                            PropertyRentFormActivity.class)
                            .putExtra("cat_id", cat_id)
                            .putExtra("sub_id", obj.getSubCatId())
                            .putExtra("sub_title", obj.getSubCatName()));
                } else if (obj.getSubCatName().contains("Sell office") ||
                        obj.getSubCatName().contains("Rent office")) {
                    startActivity(new Intent(getApplicationContext(),
                            OfficeSellRentFormActivity.class)
                            .putExtra("cat_id", cat_id)
                            .putExtra("sub_id", obj.getSubCatId())
                            .putExtra("sub_title", obj.getSubCatName()));
                } else if (obj.getSubCatName().equalsIgnoreCase("View All")) {
                    for (int i = 0; i < lstCategories.size(); i++) {
                        test.add(lstCategories.get(i).getSubCatId());
                        Log.d("--pass--", "getSubCatId: " + lstCategories.get(i).getSubCatId());
                    }
                    Log.d("--pass--", "getSubCatId: " + new Gson().toJson(test));
                    Log.d("--pass--", "getSubCatId: " + new Gson().toJson(test).replace("[", "").replace("]", ""));
                }

            } else if (cat_id == 7) {

                if (obj.getSubCatName().equalsIgnoreCase("View All")) {
                    for (int i = 0; i < lstCategories.size(); i++) {
                        test.add(lstCategories.get(i).getSubCatId());
                        Log.d("--pass--", "getSubCatId: " + lstCategories.get(i).getSubCatId());
                    }
                    Log.d("--pass--", "getSubCatId: " + new Gson().toJson(test));
                    Log.d("--pass--", "getSubCatId: " + new Gson().toJson(test).replace("[", "").replace("]", ""));

                } else {
                    startActivity(new Intent(getApplicationContext(),
                            JobFormActivity.class)
                            .putExtra("cat_id", cat_id)
                            .putExtra("sub_id", obj.getSubCatId())
                            .putExtra("sub_title", obj.getSubCatName()));
                }
            } else if (cat_id == 9) {
                if (obj.getSubCatName().contains("Bicycles") ||
                        obj.getSubCatName().contains("Spare parts")) {
                    startActivity(new Intent(getApplicationContext(),
                            AllProductForm.class)
                            .putExtra("cat_id", cat_id)
                            .putExtra("sub_id", obj.getSubCatId())
                            .putExtra("sub_title", obj.getSubCatName()));
                } else if (obj.getSubCatName().contains("Bikes") ||
                        obj.getSubCatName().contains("Scooters")) {
                    startActivity(new Intent(getApplicationContext(),
                            BikeScooterFormActivity.class)
                            .putExtra("cat_id", cat_id)
                            .putExtra("sub_id", obj.getSubCatId())
                            .putExtra("sub_title", obj.getSubCatName()));
                } else if (obj.getSubCatName().equalsIgnoreCase("View All")) {
                    for (int i = 0; i < lstCategories.size(); i++) {
                        test.add(lstCategories.get(i).getSubCatId());
                        Log.d("--pass--", "getSubCatId: " + lstCategories.get(i).getSubCatId());
                    }
                    Log.d("--pass--", "getSubCatId: " + new Gson().toJson(test));
                    Log.d("--pass--", "getSubCatId: " + new Gson().toJson(test).replace("[", "").replace("]", ""));
                }
            } else if (cat_id == 11) {
                if (obj.getSubCatName().contains("Spare parts")) {
                    startActivity(new Intent(getApplicationContext(),
                            AllProductForm.class)
                            .putExtra("cat_id", cat_id)
                            .putExtra("sub_id", obj.getSubCatId())
                            .putExtra("sub_title", obj.getSubCatName()));
                } else if (obj.getSubCatName().contains("Commercial Vehicles")) {
                    startActivity(new Intent(getApplicationContext(),
                            CommercialVehicleFormActivity.class)
                            .putExtra("cat_id", cat_id)
                            .putExtra("sub_id", obj.getSubCatId())
                            .putExtra("sub_title", obj.getSubCatName()));
                } else if (obj.getSubCatName().equalsIgnoreCase("View All")) {
                    for (int i = 0; i < lstCategories.size(); i++) {
                        test.add(lstCategories.get(i).getSubCatId());
                        Log.d("--pass--", "getSubCatId: " + lstCategories.get(i).getSubCatId());
                    }
                    Log.d("--pass--", "getSubCatId: " + new Gson().toJson(test));
                    Log.d("--pass--", "getSubCatId: " + new Gson().toJson(test).replace("[", "").replace("]", ""));
                }
            } else if (cat_id == 8 || cat_id == 10 || cat_id == 12 || cat_id == 13 || cat_id == 14 || cat_id == 15) {

                if (obj.getSubCatName().equalsIgnoreCase("View All")) {
                    for (int i = 0; i < lstCategories.size(); i++) {
                        test.add(lstCategories.get(i).getSubCatId());
                        Log.d("--pass--", "getSubCatId: " + lstCategories.get(i).getSubCatId());
                    }
                    Log.d("--pass--", "getSubCatId: " + new Gson().toJson(test));
                    Log.d("--pass--", "getSubCatId: " + new Gson().toJson(test).replace("[", "").replace("]", ""));

                } else {
                    startActivity(new Intent(getApplicationContext(),
                            AllProductForm.class)
                            .putExtra("cat_id", cat_id)
                            .putExtra("sub_id", obj.getSubCatId())
                            .putExtra("sub_title", obj.getSubCatName()));
                }

            }

        });
    }*/


    List<ClsSubCategories> lstCategories = new ArrayList<>();

    void callSubCategoryByIDAPI(int cat_id) {
        pd.show();
        saleViewModel.getSubCategoriesByID(cat_id).observe(this, lst -> {
            if (lst.getSuccess() == 1) {
                if (lst.getData().size() != 0) {
                    lstCategories = lst.getData();
                    if (lstCategories.size() > 0) {
                        if (mode != null && mode.equalsIgnoreCase("Purchase")) {
                            lstCategories.add(lstCategories.size(), new ClsSubCategories(1111, cat_id, "View All", title_name));
                        }
                        mAdapter.AddItems(lstCategories);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "No Sub Categories Founds...!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Data is not founds...!", Toast.LENGTH_SHORT).show();
            }
            pd.dismiss();
        });
    }

}
