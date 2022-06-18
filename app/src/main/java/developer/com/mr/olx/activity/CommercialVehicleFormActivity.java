package developer.com.mr.olx.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import developer.com.mr.olx.Interfaces.ItemClickListener;
import developer.com.mr.olx.ModelClasses.MultipartCommVehicleFormAPI;
import developer.com.mr.olx.ModelClasses.clsPhoto;
import developer.com.mr.olx.R;
import developer.com.mr.olx.adapter.UriAdapter;
import developer.com.mr.olx.clsApiClasses.ClsCommercialVehicleParams;
import developer.com.mr.olx.cropClass.CropActivity;
import developer.com.mr.olx.global.ClsGlobal;
import developer.com.mr.olx.matisse.Matisse;
import developer.com.mr.olx.productsList.ApiPostViewModel;
import developer.com.mr.olx.productsList.ClsInventoryMasterFormData;

public class CommercialVehicleFormActivity extends AppCompatActivity {
    String sub_title = "";
    TextView txt_title, txt_select_type;
    LinearLayout ll_vehicle_type;

    EditText edt_insurance_name, edt_location, edt_description, edt_title;
    EditText edt_year, edt_price, edt_brand, edt_kms;
    LinearLayout ll_insurance_type;

    private RadioGroup rg_insurance;
    private RadioButton rb_insurance_yes, rb_insurance_no;
    String getInsuranceVal = "";
    boolean getInsuranceValBool;
    int getInsuranceValInt;

    private RadioGroup rg_mobile_no_group;
    private RadioButton rb_mobile_yes, rb_mobile_no;
    String getMobileNoVal = "";
    boolean getMobileNoValBool;
    int getMobileNoValInt;

    private ApiPostViewModel apiPostViewModel;
    private ProgressDialog pd;
    int cat_id = 0;
    int sub_id = 0;
    ImageView iv_add_image;
    private static final int REQUEST_CODE_CHOOSE = 23;
    public ArrayList<clsPhoto> listPhoto = new ArrayList<>();
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    ArrayList arrayList = new ArrayList();
    RecyclerView recyclerView;
    private UriAdapter mAdapter;

    private int getChangePosition = 0;
    String selectImg = "";

    String test_selectedPath = "";

    Bitmap bitmap1;
    Bitmap bitmap2;
    Bitmap bitmap3;
    Bitmap bitmap4;
    Bitmap bitmap5;
    Bitmap bitmap6;
    Bitmap bitmap7;
    Bitmap bitmap8;
    Bitmap bitmap9;
    Bitmap bitmap10;

    Button btn_post;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commercial_vehicle_form);

        apiPostViewModel = new ViewModelProvider(this).get(ApiPostViewModel.class);
        sub_title = getIntent().getStringExtra("sub_title");
        cat_id = getIntent().getIntExtra("cat_id", 0);
        sub_id = getIntent().getIntExtra("sub_id", 0);
        ll_vehicle_type = findViewById(R.id.ll_vehicle_type);
        txt_title = findViewById(R.id.txt_title);
        txt_title.setText("Add " + sub_title + " details");


        edt_insurance_name = findViewById(R.id.edt_insurance_name);
        ll_insurance_type = findViewById(R.id.ll_insurance_type);
        rg_insurance = findViewById(R.id.rg_insurance);
        rb_insurance_yes = findViewById(R.id.rb_insurance_yes);
        rb_insurance_no = findViewById(R.id.rb_insurance_no);
        edt_year = findViewById(R.id.edt_year);
        edt_location = findViewById(R.id.edt_location);
        edt_description = findViewById(R.id.edt_description);
        edt_brand = findViewById(R.id.edt_brand);

        rg_mobile_no_group = findViewById(R.id.rg_mobile_no_group);
        rb_mobile_yes = findViewById(R.id.rb_mobile_yes);
        rb_mobile_no = findViewById(R.id.rb_mobile_no);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        iv_add_image = findViewById(R.id.iv_add_image);
        btn_post = findViewById(R.id.btn_post);
        edt_title = findViewById(R.id.edt_title);
        edt_price = findViewById(R.id.edt_price);
        edt_kms = findViewById(R.id.edt_kms);
        txt_select_type = findViewById(R.id.txt_select_type);

        txt_select_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectVehicleType();
            }
        });
        iv_add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listPhoto.size() != 0 && listPhoto.size() > 10) {
                    ClsGlobal.errorMsg(CommercialVehicleFormActivity.this, v, "Select at least 10 photo...!");
                    return;
                }
                ClsGlobal.OnImageSelection(CommercialVehicleFormActivity.this, REQUEST_CODE_CHOOSE);
            }
        });

        mAdapter = new UriAdapter(CommercialVehicleFormActivity.this, listPhoto);
        recyclerView.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(String selectedPath, int position) {
                openCropActivityForResult(selectedPath, position);
                test_selectedPath = selectedPath;
            }

            @Override
            public void onItemClick(Uri path, String selectedPath, int position) {
                openCropActivityForResult(selectedPath, position);
            }

            @Override
            public void onItemDeleteClick(String selectedPath, int position) {
                listPhoto.remove(position);
                mAdapter.notifyItemRemoved(position);
                mAdapter.notifyItemChanged(position);
                mAdapter.notifyDataSetChanged();
            }
        });

        rg_mobile_no_group.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_mobile_yes) {
                getMobileNoVal = rb_mobile_yes.getText().toString();
                getMobileNoValBool = true;
                getMobileNoValInt = 1;
            } else {
                getMobileNoVal = rb_mobile_no.getText().toString();
                getMobileNoValBool = false;
                getMobileNoValInt = 0;
            }
        });

        rg_insurance.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_insurance_yes) {
                ll_insurance_type.setVisibility(View.VISIBLE);
                getInsuranceVal = rb_insurance_yes.getText().toString();
                getInsuranceValBool = true;
                getInsuranceValInt = 1;

            } else {
                ll_insurance_type.setVisibility(View.GONE);
                edt_insurance_name.getText().clear();
                getInsuranceVal = rb_insurance_no.getText().toString();
                getInsuranceValBool = false;
                getInsuranceValInt = 0;
            }
        });

        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation(view)) {
                    try {
                        callNewBtnClick();
                    } catch (Exception e) {
                        Log.e("--Mobile--", "Exception: " + e.getMessage());
                    }
                } else {
                    Toast.makeText(CommercialVehicleFormActivity.this, "Fill required value...!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private Boolean validation(View view) {

        if (edt_title.getText() == null || edt_title.getText().toString().trim().isEmpty()) {
            ClsGlobal.errorMsg(this, view, "Title is required...!");
            edt_title.requestFocus();
            return false;
        }

        if (edt_price.getText() == null || edt_price.getText().toString().trim().isEmpty()) {
            ClsGlobal.errorMsg(this, view, "Price is required...!");
            edt_price.requestFocus();
            return false;
        }

        if (txt_select_type.getText() == null || txt_select_type.getText().toString().trim().isEmpty()) {
            ClsGlobal.errorMsg(this, view, "Type is required...!");
            txt_select_type.requestFocus();
            return false;
        }

        if (edt_brand.getText() == null || edt_brand.getText().toString().trim().isEmpty()) {
            ClsGlobal.errorMsg(this, view, "Brand is required...!");
            edt_brand.requestFocus();
            return false;
        }

        if (edt_kms.getText() == null || edt_kms.getText().toString().trim().isEmpty()) {
            ClsGlobal.errorMsg(this, view, "kilometer is required...!");
            edt_kms.requestFocus();
            return false;
        }
        if (getInsuranceValBool) {
            if (edt_insurance_name.getText() == null || edt_insurance_name.getText().toString().trim().isEmpty()) {
                ClsGlobal.errorMsg(this, view, "Insurance name is required...!");
                edt_insurance_name.requestFocus();
                return false;
            }
        }

       /* if (listPhoto.size() != 0 && listPhoto.size() > 10) {
            errorMsg(view, "Select at least 9 photo...!");
            return false;
        }*/
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            selectedPhotos.clear();
            ArrayList<String> arrayList = (ArrayList) Matisse.obtainPathResult(data);
            selectedPhotos = arrayList;
            if (arrayList != null && !arrayList.isEmpty()) {
                for (int i3 = 0; i3 < this.selectedPhotos.size(); i3++) {
//                    listPhoto.add(new clsPhoto((long) i3, selectedPhotos.get(i3)));
                    listPhoto.add(new clsPhoto(i3, selectedPhotos.get(i3),
                            BitmapFactory.decodeFile(selectedPhotos.get(i3))));

                    Log.d("--URL--",
                            "getPath: " + selectedPhotos.get(i3));
                }
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    ActivityResultLauncher<Intent> getCropListData = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == 123) {
                    Intent data = result.getData();
                    if (data != null) {
                        getChangePosition = data.getIntExtra("POSITION", 0);
                        selectImg = data.getStringExtra("selectImg");
                        listPhoto.set(getChangePosition, new clsPhoto(getChangePosition, selectImg, ClsGlobal.mBitmap));
                        mAdapter.notifyDataSetChanged();
                    }
                }
            });

    public void openCropActivityForResult(String selectedPath, int position) {

        Intent intent = new Intent(getApplicationContext(), CropActivity.class);
        intent.putExtra("selectImg", selectedPath);
        intent.putStringArrayListExtra("PHOTO", arrayList);
        intent.putExtra("POSITION", position);
        for (int i = 0; i < listPhoto.size(); i++) {
            arrayList.add(listPhoto.get(i).getPaths());
        }
        if (position < arrayList.size()) {
            getCropListData.launch(intent);
        }
    }

    void selectVehicleType() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CommercialVehicleFormActivity.this);
        List<String> lstBrandName = new ArrayList<>();
        lstBrandName.add("Petrol");
        lstBrandName.add("Diesel");
        lstBrandName.add("CNG");
        lstBrandName.add("LPG");
        lstBrandName.add("Electronic");
        lstBrandName.add("6th");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.row_dialog_label, lstBrandName);

        builder.setAdapter(dataAdapter, (dialog, which) -> {
            txt_select_type.setText(lstBrandName.get(which));
            txt_select_type.setTag(which);
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    void callOldBtnClick() {
        try {
            for (int i = 0; i < listPhoto.size(); i++) {
                bitmap1 = listPhoto.get(0).getmBitmap();
                bitmap2 = listPhoto.get(1).getmBitmap();
                bitmap3 = listPhoto.get(2).getmBitmap();
                            /*bitmap4 = listPhoto.get(3).getmBitmap();
                            bitmap5 = listPhoto.get(4).getmBitmap();
                            bitmap6 = listPhoto.get(5).getmBitmap();
                            bitmap7 = listPhoto.get(6).getmBitmap();
                            bitmap8 = listPhoto.get(7).getmBitmap();
                            bitmap9 = listPhoto.get(8).getmBitmap();
                            bitmap10 = listPhoto.get(9).getmBitmap();*/
            }
        } catch (Exception e) {
            Log.e("--Mobile--", "Exception: " + e.getMessage());
        }
        BikeScooterFormAPI();
    }

    String baseUrlDemo = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAApgAAAKYB3X3/OAAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAANCSURBVEiJtZZPbBtFFMZ/M7ubXdtdb1xSFyeilBapySVU8h8OoFaooFSqiihIVIpQBKci6KEg9Q6H9kovIHoCIVQJJCKE1ENFjnAgcaSGC6rEnxBwA04Tx43t2FnvDAfjkNibxgHxnWb2e/u992bee7tCa00YFsffekFY+nUzFtjW0LrvjRXrCDIAaPLlW0nHL0SsZtVoaF98mLrx3pdhOqLtYPHChahZcYYO7KvPFxvRl5XPp1sN3adWiD1ZAqD6XYK1b/dvE5IWryTt2udLFedwc1+9kLp+vbbpoDh+6TklxBeAi9TL0taeWpdmZzQDry0AcO+jQ12RyohqqoYoo8RDwJrU+qXkjWtfi8Xxt58BdQuwQs9qC/afLwCw8tnQbqYAPsgxE1S6F3EAIXux2oQFKm0ihMsOF71dHYx+f3NND68ghCu1YIoePPQN1pGRABkJ6Bus96CutRZMydTl+TvuiRW1m3n0eDl0vRPcEysqdXn+jsQPsrHMquGeXEaY4Yk4wxWcY5V/9scqOMOVUFthatyTy8QyqwZ+kDURKoMWxNKr2EeqVKcTNOajqKoBgOE28U4tdQl5p5bwCw7BWquaZSzAPlwjlithJtp3pTImSqQRrb2Z8PHGigD4RZuNX6JYj6wj7O4TFLbCO/Mn/m8R+h6rYSUb3ekokRY6f/YukArN979jcW+V/S8g0eT/N3VN3kTqWbQ428m9/8k0P/1aIhF36PccEl6EhOcAUCrXKZXXWS3XKd2vc/TRBG9O5ELC17MmWubD2nKhUKZa26Ba2+D3P+4/MNCFwg59oWVeYhkzgN/JDR8deKBoD7Y+ljEjGZ0sosXVTvbc6RHirr2reNy1OXd6pJsQ+gqjk8VWFYmHrwBzW/n+uMPFiRwHB2I7ih8ciHFxIkd/3Omk5tCDV1t+2nNu5sxxpDFNx+huNhVT3/zMDz8usXC3ddaHBj1GHj/As08fwTS7Kt1HBTmyN29vdwAw+/wbwLVOJ3uAD1wi/dUH7Qei66PfyuRj4Ik9is+hglfbkbfR3cnZm7chlUWLdwmprtCohX4HUtlOcQjLYCu+fzGJH2QRKvP3UNz8bWk1qMxjGTOMThZ3kvgLI5AzFfo379UAAAAASUVORK5CYII=";

    void BikeScooterFormAPI() {

        pd = ClsGlobal._prProgressDialog(this, "Adding new Product for sale...", false);
        pd.show();

        ClsCommercialVehicleParams mObj = new ClsCommercialVehicleParams();
        mObj.setVehicelInventoryId(0);
        mObj.setUserId(1);
        mObj.setCatId(cat_id);
        mObj.setSubCatId(sub_id);
        mObj.setvTitle(edt_title.getText().toString());
        mObj.setvPrice(Double.valueOf(edt_price.getText().toString()));
        mObj.setBrand(edt_brand.getText().toString());
        mObj.setKms(Integer.valueOf(edt_kms.getText().toString()));
        mObj.setYear(Integer.valueOf(edt_year.getText().toString()));
        mObj.setvShowMoNo(getMobileNoValBool);

        if (bitmap1 != null) {
            mObj.setvPictureLink1("data:image/png;base64," + ClsGlobal.getBase64Drawable(bitmap1));
        } else {
            mObj.setvPictureLink1("");
        }

        if (bitmap2 != null) {
            mObj.setvPictureLink2("data:image/png;base64," + ClsGlobal.getBase64Drawable(bitmap2));
        } else {
            mObj.setvPictureLink2("");
        }

        if (bitmap3 != null) {
            mObj.setvPictureLink3("data:image/png;base64," + ClsGlobal.getBase64Drawable(bitmap3));
        } else {
            mObj.setvPictureLink4("");
        }

        mObj.setvPictureLink4(baseUrlDemo);
        mObj.setvPictureLink5(baseUrlDemo);
        mObj.setvPictureLink6(baseUrlDemo);
        mObj.setvPictureLink7(baseUrlDemo);
        mObj.setvPictureLink8(baseUrlDemo);
        mObj.setvPictureLink9(baseUrlDemo);
        mObj.setvPictureLink10(baseUrlDemo);

        mObj.setInsuarance(getInsuranceValBool);
        mObj.setiDescription(edt_insurance_name.getText().toString());
        mObj.setvDescription(edt_description.getText().toString());
        mObj.setvLocation(edt_location.getText().toString());

        mObj.setLatitude(0.0);
        mObj.setLongitude(0.0);

        Log.e("--Property--", "Gson: " + new Gson().toJson(mObj));

        apiPostViewModel.FillCommercialVehicleForm(mObj).observe(this, obj -> {
            if (obj != null) {
                int message = obj.getSuccess();
                if (message != 1) {
                    Toast.makeText(this, "Failed to add product...!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Your product is add successfully...!", Toast.LENGTH_SHORT).show();
                }
            }
            pd.dismiss();
        });
    }

    String file1 = "", file2 = "", file3 = "", file4 = "", file5 = "";
    String file6 = "", file7 = "", file8 = "", file9 = "", file10 = "";

    void callNewBtnClick() {
        try {
            for (int i = 0; i < listPhoto.size(); i++) {
                file1 = listPhoto.get(0).getPaths();
                file2 = listPhoto.get(1).getPaths();
                file3 = listPhoto.get(2).getPaths();
                file4 = listPhoto.get(3).getPaths();
                file5 = listPhoto.get(4).getPaths();
                file6 = listPhoto.get(5).getPaths();
                file7 = listPhoto.get(6).getPaths();
                file8 = listPhoto.get(7).getPaths();
                file9 = listPhoto.get(8).getPaths();
                file10 = listPhoto.get(9).getPaths();
            }
        } catch (Exception e) {
            Log.e("--Mobile--", "Exception: " + e.getMessage());
        }

        saveMultipleImages();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                uploadMultipleImageAPI(new File(file1), new File(file2), new File(file3), new File(file4)
                        , new File(file5), new File(file6), new File(file7), new File(file8), new File(file9), new File(file10));
            }
        }, 2000);
    }


    void saveMultipleImages() {
        ArrayList<Bitmap> bmpArray = new ArrayList<>();
        pd = ClsGlobal._prProgressDialog(CommercialVehicleFormActivity.this,
                "please wait for upload images...!", false);
        pd.show();
        for (int i3 = 0; i3 < this.listPhoto.size(); i3++) {
            bmpArray.add(listPhoto.get(i3).getmBitmap());
        }
        ClsGlobal.storeMultipleImage(CommercialVehicleFormActivity.this, bmpArray);
    }

    void uploadMultipleImageAPI(File mFile1, File mFile2, File mFile3, File mFile4, File mFile5,
                                File mFile6, File mFile7, File mFile8, File mFile9, File mFile10) {

        MultipartCommVehicleFormAPI multipleImageUploader = new MultipartCommVehicleFormAPI(CommercialVehicleFormActivity.this);
        multipleImageUploader.uploadFormDataFormAPI(0, 1, edt_title.getText().toString(), Double.parseDouble(edt_price.getText().toString()),
                mFile1, mFile2, mFile3, mFile4, mFile5, mFile6, mFile7, mFile8, mFile9, mFile10,
                Integer.parseInt(txt_select_type.getTag().toString()), edt_brand.getText().toString(),
                Double.parseDouble(edt_kms.getText().toString()), edt_year.getText().toString(),
                getInsuranceValInt, edt_insurance_name.getText().toString(), getMobileNoValInt, edt_description.getText().toString(),
                edt_location.getText().toString(), Double.parseDouble("12.2365"), Double.parseDouble("78.2558"), cat_id, sub_id);

        multipleImageUploader.showUploadProgressBar(false);
        multipleImageUploader.SetCallBack(new MultipartCommVehicleFormAPI.FileUploaderCallback() {
            @Override
            public void onError() {
                if (pd.isShowing()) {
                    pd.dismiss();
                }
                Log.d("--path--", "step 17");
            }

            @Override
            public void onFinish(ClsInventoryMasterFormData responses) {
                if (responses.getSuccess() == 1) {
                    if (pd.isShowing()) {
                        pd.dismiss();
                    }
                    ClsGlobal.deleteMultipleImagesFromFolder(CommercialVehicleFormActivity.this);
                }
            }

            @Override
            public void onProgressUpdate(int currentpercent, int totalpercent, String msg) {
                if (pd.isShowing()) {
                    pd.dismiss();
                }
                Log.e("--path--", "currentpercent: " + currentpercent);
                Log.e("--path--", "totalpercent: " + totalpercent);
            }
        });
    }


}
