package developer.com.mr.olx.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import developer.com.mr.olx.Interfaces.ItemClickListener;
import developer.com.mr.olx.ModelClasses.MultipartAllFormAPI;
import developer.com.mr.olx.ModelClasses.clsPhoto;
import developer.com.mr.olx.R;
import developer.com.mr.olx.adapter.UriAdapter;
import developer.com.mr.olx.clsApiClasses.clsAddProductParams;
import developer.com.mr.olx.cropClass.CropActivity;
import developer.com.mr.olx.global.ClsGlobal;
import developer.com.mr.olx.global.ClsPreferencesInfo;
import developer.com.mr.olx.globalApi.AddProductViewModel;
import developer.com.mr.olx.matisse.Matisse;
import developer.com.mr.olx.matisse.MimeType;
import developer.com.mr.olx.matisse.engine.impl.GlideEngine;
import developer.com.mr.olx.matisse.internal.entity.CaptureStrategy;
import developer.com.mr.olx.productsList.ApiPostViewModel;
import developer.com.mr.olx.productsList.ClsInventoryMasterFormData;
import developer.com.mr.olx.productsList.clsInventoryMaster;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

public class AllProductForm extends AppCompatActivity {


    private static final int REQUEST_CODE_CHOOSE = 23;
    ImageView iv_back, iv_change_location;
    ImageView iv_add_image;
    private AddProductViewModel addProductViewModel;
    private ApiPostViewModel apiPostViewModel;
    EditText edt_title, edt_price, edt_description;
    Button btn_add;
    Boolean _Selected = false;
    RecyclerView recyclerView;
    private UriAdapter mAdapter;
    String sub_title = "";
    TextView txt_title;
    TextView edt_location;

    public ArrayList<clsPhoto> listPhoto = new ArrayList<>();
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    ArrayList arrayList = new ArrayList();
    private ProgressDialog pd;
    private RadioGroup rg_mobile_no_group;
    private RadioButton rb_mobile_yes, rb_mobile_no;
    String getMobileNoVal = "";
    boolean getMobileNoValBool;
    int getMobileNoValInt;
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

    int cat_id = 0;
    int sub_id = 0;
    List<Integer> sub_id_list = new ArrayList<>();
    ClsPreferencesInfo obj;

    double get_latitude = 0.0, get_longitude = 0.0;
    String get_location = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product_information);
        addProductViewModel = new ViewModelProvider(this).get(AddProductViewModel.class);
        apiPostViewModel = new ViewModelProvider(this).get(ApiPostViewModel.class);
        obj = new ClsPreferencesInfo();
        obj = ClsGlobal.getPreferencesInfo(AllProductForm.this);

        get_latitude = obj.getCurrentLatitude();
        get_longitude = obj.getCurrentLongitude();
        get_location = obj.getCurrentLocation();

        Log.d("--FillAPI--", "UserCode: " + new Gson().toJson(obj));
        sub_title = getIntent().getStringExtra("sub_title");
        cat_id = getIntent().getIntExtra("cat_id", 0);
        sub_id = getIntent().getIntExtra("sub_id", 0);

        txt_title = findViewById(R.id.txt_title);
        txt_title.setText("Add " + sub_title + " details");
        sub_id_list = (ArrayList<Integer>) getIntent().getSerializableExtra("sub_id_list");
        Log.d("--pass--", "AllProduct: " + new Gson().toJson(sub_id_list).replace("[", "").replace("]", ""));

        iv_change_location = findViewById(R.id.iv_change_location);
        edt_location = findViewById(R.id.edt_location);
        btn_add = findViewById(R.id.btn_add);
        edt_price = findViewById(R.id.edt_price);
        rg_mobile_no_group = findViewById(R.id.rg_mobile_no_group);
        rb_mobile_yes = findViewById(R.id.rb_mobile_yes);
        rb_mobile_no = findViewById(R.id.rb_mobile_no);
        edt_title = findViewById(R.id.edt_title);
        edt_description = findViewById(R.id.edt_description);
        iv_add_image = findViewById(R.id.iv_add_image);
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(v -> finish());
        edt_location.setText(get_location);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);

        iv_change_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLocationActivityForResult();
            }
        });

        iv_add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnImageSelection();
            }
        });

        mAdapter = new UriAdapter(AllProductForm.this, listPhoto);
        recyclerView.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(String selectedPath, int position) {
//                startActivity(new Intent(getApplicationContext(), CropActivity.class).putExtra("selectImg", selectedPath));
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


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                fillProductInformation();
//
//                callOldBtnClick();


                if (validation(view)) {
                    try {
                        callNewBtnClick();
                    } catch (Exception e) {
                        Log.e("--Mobile--", "Exception: " + e.getMessage());
                    }
                } else {
                    Toast.makeText(AllProductForm.this, "Fill required value...!", Toast.LENGTH_SHORT).show();
                }

//                startActivity(new Intent(getApplicationContext(), SetProfileDataActivity.class));

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

    }

    File finalFile = new File("");
    Bitmap bmp;


    void callOldBtnClick() {
        try {
            for (int i = 0; i < listPhoto.size(); i++) {
                      /*
                        bitmap1 = clsGlobal.getResizedBitmap(listPhoto.get(0).getmBitmap(), 500);
                        bitmap2 = clsGlobal.getResizedBitmap(listPhoto.get(1).getmBitmap(), 500);
                        bitmap3 = clsGlobal.getResizedBitmap(listPhoto.get(2).getmBitmap(), 500);
                        bitmap4 = clsGlobal.getResizedBitmap(listPhoto.get(3).getmBitmap(), 500);
                        bitmap5 = clsGlobal.getResizedBitmap(listPhoto.get(4).getmBitmap(), 500);
                        bitmap6 = clsGlobal.getResizedBitmap(listPhoto.get(5).getmBitmap(), 500);
                        bitmap7 = clsGlobal.getResizedBitmap(listPhoto.get(6).getmBitmap(), 500);
                        bitmap8 = clsGlobal.getResizedBitmap(listPhoto.get(7).getmBitmap(), 500);
                        bitmap9 = clsGlobal.getResizedBitmap(listPhoto.get(8).getmBitmap(), 500);
                        bitmap10 = clsGlobal.getResizedBitmap(listPhoto.get(9).getmBitmap(), 600);

                        */
                bitmap1 = listPhoto.get(0).getmBitmap();
                bitmap2 = listPhoto.get(1).getmBitmap();
                bitmap3 = listPhoto.get(2).getmBitmap();
                bitmap4 = listPhoto.get(3).getmBitmap();
                bitmap5 = listPhoto.get(4).getmBitmap();
                bitmap6 = listPhoto.get(5).getmBitmap();
                bitmap7 = listPhoto.get(6).getmBitmap();
                bitmap8 = listPhoto.get(7).getmBitmap();
                bitmap9 = listPhoto.get(8).getmBitmap();
                bitmap10 = listPhoto.get(9).getmBitmap();

            }
        } catch (Exception e) {
            Log.e("Check", "Exception: " + e.getMessage());
        }
        getAddProductInfo();
    }

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

    private ArrayList<Bitmap> bmpArray = new ArrayList<>();

    void saveMultipleImages() {
        bmpArray = new ArrayList<>();
        pd = ClsGlobal._prProgressDialog(AllProductForm.this,
                "please wait for upload images...!", false);
        pd.show();
        for (int i3 = 0; i3 < this.listPhoto.size(); i3++) {
            bmpArray.add(listPhoto.get(i3).getmBitmap());
        }
        ClsGlobal.storeMultipleImage(AllProductForm.this, bmpArray);
    }

    String file1 = "", file2 = "", file3 = "", file4 = "", file5 = "";
    String file6 = "", file7 = "", file8 = "", file9 = "", file10 = "";

    void uploadMultipleImageAPI(File mFile1, File mFile2, File mFile3, File mFile4,
                                File mFile5, File mFile6, File mFile7, File mFile8,
                                File mFile9, File mFile10) {

        MultipartAllFormAPI multipleImageUploader = new MultipartAllFormAPI(AllProductForm.this);
        multipleImageUploader.uploadFormDataFormAPI(0, 1, cat_id, sub_id, edt_title.getText().toString(),
                Double.parseDouble(edt_price.getText().toString()), mFile1, mFile2, mFile3, mFile4, mFile5, mFile6,
                mFile7, mFile8, mFile9, mFile10, getMobileNoValInt, edt_description.getText().toString(),
                "Vesu surat,Gujarat",
                Double.parseDouble("20.621813"), Double.parseDouble("78.946523"));
//                edt_location.getText().toString(),
//                get_latitude, get_longitude);

        multipleImageUploader.showUploadProgressBar(false);
        multipleImageUploader.SetCallBack(new MultipartAllFormAPI.FileUploaderCallback() {
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
                    ClsGlobal.deleteMultipleImagesFromFolder(AllProductForm.this);
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

    private void storeImage() {
        try {
            String imagesDir = getExternalFilesDir(null).getAbsolutePath();

            File file = new File(imagesDir);

            if (!file.exists()) {
                file.mkdir();
            }

            finalFile = new File(imagesDir, "Payment.jpg");
            FileOutputStream fos = new FileOutputStream(finalFile);

            if (bmp != null) {
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            }
            fos.flush();
            fos.close();
        } catch (Exception e) {
            Log.e("Check", "Exception: " + e.getMessage());
        }
    }

    void OnImageSelection() {
        Matisse.from(AllProductForm.this)
                .choose(MimeType.ofImage(), false)
                .countable(true)
                .captureStrategy(
                        new CaptureStrategy(true, "developer.com.mr.olx.fileprovider", "test"))
                .maxSelectable(10)
                .gridExpectedSize(
                        getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .showSingleMediaType(true)
                .originalEnable(true)
                .maxOriginalSize(10)
                .autoHideToolbarOnSingleTap(true)
                .forResult(REQUEST_CODE_CHOOSE);
    }


    private ArrayList<String> photos = null;
    private int getChangePosition = 0;
    String selectImg = "";

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

    public void openLocationActivityForResult() {
        getLocation.launch(new Intent(getApplicationContext(), GetLocationActivity.class)
                .putExtra("location_mode", "inForm"));
    }

    ActivityResultLauncher<Intent> getLocation = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == 9990999) {
                    Intent data = result.getData();
                    if (data != null) {
                        get_latitude = data.getDoubleExtra("get_latitude", 0.0);
                        get_longitude = data.getDoubleExtra("get_longitude", 0.0);
                        get_location = data.getStringExtra("get_location");
                        edt_location.setText(get_location);
                    }
                }
            });

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            selectedPhotos.clear();
            ArrayList<String> arrayList = (ArrayList) Matisse.obtainPathResult(data);
            selectedPhotos = arrayList;
            if (arrayList != null && !arrayList.isEmpty()) {
                for (int i3 = 0; i3 < this.selectedPhotos.size(); i3++) {
                    listPhoto.add(new clsPhoto(i3, selectedPhotos.get(i3),
                            BitmapFactory.decodeFile(selectedPhotos.get(i3))));
                }
            }
            mAdapter.notifyDataSetChanged();
        }
    }


    String baseUrlDemo = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAApgAAAKYB3X3/OAAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAANCSURBVEiJtZZPbBtFFMZ/M7ubXdtdb1xSFyeilBapySVU8h8OoFaooFSqiihIVIpQBKci6KEg9Q6H9kovIHoCIVQJJCKE1ENFjnAgcaSGC6rEnxBwA04Tx43t2FnvDAfjkNibxgHxnWb2e/u992bee7tCa00YFsffekFY+nUzFtjW0LrvjRXrCDIAaPLlW0nHL0SsZtVoaF98mLrx3pdhOqLtYPHChahZcYYO7KvPFxvRl5XPp1sN3adWiD1ZAqD6XYK1b/dvE5IWryTt2udLFedwc1+9kLp+vbbpoDh+6TklxBeAi9TL0taeWpdmZzQDry0AcO+jQ12RyohqqoYoo8RDwJrU+qXkjWtfi8Xxt58BdQuwQs9qC/afLwCw8tnQbqYAPsgxE1S6F3EAIXux2oQFKm0ihMsOF71dHYx+f3NND68ghCu1YIoePPQN1pGRABkJ6Bus96CutRZMydTl+TvuiRW1m3n0eDl0vRPcEysqdXn+jsQPsrHMquGeXEaY4Yk4wxWcY5V/9scqOMOVUFthatyTy8QyqwZ+kDURKoMWxNKr2EeqVKcTNOajqKoBgOE28U4tdQl5p5bwCw7BWquaZSzAPlwjlithJtp3pTImSqQRrb2Z8PHGigD4RZuNX6JYj6wj7O4TFLbCO/Mn/m8R+h6rYSUb3ekokRY6f/YukArN979jcW+V/S8g0eT/N3VN3kTqWbQ428m9/8k0P/1aIhF36PccEl6EhOcAUCrXKZXXWS3XKd2vc/TRBG9O5ELC17MmWubD2nKhUKZa26Ba2+D3P+4/MNCFwg59oWVeYhkzgN/JDR8deKBoD7Y+ljEjGZ0sosXVTvbc6RHirr2reNy1OXd6pJsQ+gqjk8VWFYmHrwBzW/n+uMPFiRwHB2I7ih8ciHFxIkd/3Omk5tCDV1t+2nNu5sxxpDFNx+huNhVT3/zMDz8usXC3ddaHBj1GHj/As08fwTS7Kt1HBTmyN29vdwAw+/wbwLVOJ3uAD1wi/dUH7Qei66PfyuRj4Ik9is+hglfbkbfR3cnZm7chlUWLdwmprtCohX4HUtlOcQjLYCu+fzGJH2QRKvP3UNz8bWk1qMxjGTOMThZ3kvgLI5AzFfo379UAAAAASUVORK5CYII=";
    String testBaseURL = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAkAAAAJACAYAAABlmtk2AAAAAXNSR0IArs4c6QAAQABJREFUeAHt3QuUJeddGPiqe7t73jOa0YwkyzZYxkI4Y2ltj/UYy4IBSRiJxCe244UTL0ReiGF3WdgkLPGBcDaBQBbWe7IhEBLs3dgKNklYxMP4BVIQkuwZSR4MkgZsLGyt40S25qF5v7r71n5fWzXq6enXrVv33nr86pyZ21331vf9v9//dte/q+rWlyQWAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAgAABAgQIECBAIBdI8y88EmiKwPf9+I9v2LHx8pNNGY9xEBi1QNabfcs//5l/9JFR96s/AqMU6IyyM30RIECAAAECBKogoACqQhbEQIAAAQIECIxUQAE0Um6dESBAgAABAlUQUABVIQtiIECAAAECBEYqoAAaKbfOCBAgQIAAgSoIKICqkAUxECBAgAABAiMVUACNlFtnBAgQIECAQBUEFEBVyIIYCBAgQIAAgZEKKIBGyq0zAgQIECBAoAoCCqAqZEEMBAgQIECAwEgFFEAj5dYZAQIECBAgUAUBBVAVsiAGAgQIECBAYKQCCqCRcuuMAAECBAgQqIKAAqgK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";

    void getAddProductInfo() {

        pd = ClsGlobal._prProgressDialog(this, "Adding new Product for sale...", false);
        pd.show();

        clsInventoryMaster mObj = new clsInventoryMaster();
        mObj.setAllInventoryId(0);
        mObj.setUserId(1);
        mObj.setCatId(cat_id);
        mObj.setSubCatId(sub_id);
        mObj.setTitle(edt_title.getText().toString());
        mObj.setPrice(Integer.valueOf(edt_price.getText().toString()));
        if (bitmap1 != null) {
            mObj.setPictureLink1("data:image/png;base64," + ClsGlobal.getBase64Drawable(bitmap1));
        } else {
            mObj.setPictureLink1("");
        }

        if (bitmap2 != null) {
            mObj.setPictureLink2("data:image/png;base64," + ClsGlobal.getBase64Drawable(bitmap2));
        } else {
            mObj.setPictureLink2("");
        }

        if (bitmap3 != null) {
            mObj.setPictureLink3("data:image/png;base64," + ClsGlobal.getBase64Drawable(bitmap3));
        } else {
            mObj.setPictureLink3("");
        }

        if (bitmap4 != null) {
            mObj.setPictureLink4("data:image/png;base64," + ClsGlobal.getBase64Drawable(bitmap4));
        } else {
            mObj.setPictureLink4("");
        }

        if (bitmap5 != null) {
            mObj.setPictureLink5("data:image/png;base64," + ClsGlobal.getBase64Drawable(bitmap5));
        } else {
            mObj.setPictureLink5("");
        }

        if (bitmap6 != null) {
            mObj.setPictureLink6("data:image/png;base64," + ClsGlobal.getBase64Drawable(bitmap6));
        } else {
            mObj.setPictureLink6("");
        }

        if (bitmap7 != null) {
            mObj.setPictureLink7("data:image/png;base64," + ClsGlobal.getBase64Drawable(bitmap7));
        } else {
            mObj.setPictureLink7("");
        }
        if (bitmap8 != null) {
            mObj.setPictureLink8("data:image/png;base64," + ClsGlobal.getBase64Drawable(bitmap8));
        } else {
            mObj.setPictureLink8("");
        }

        if (bitmap9 != null) {
            mObj.setPictureLink9("data:image/png;base64," + ClsGlobal.getBase64Drawable(bitmap9));
        } else {
            mObj.setPictureLink9("");
        }

        if (bitmap10 != null) {
            mObj.setPictureLink10("data:image/png;base64," + ClsGlobal.getBase64Drawable(bitmap10));
        } else {
            mObj.setPictureLink10("");
        }

        mObj.setShowMoNo(getMobileNoValBool);
        mObj.setDescription(edt_description.getText().toString());
        mObj.setLocation(edt_location.getText().toString());

        apiPostViewModel.AddNewInventory(mObj).observe(this, obj -> {
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

       /* if (listPhoto.size() != 0 && listPhoto.size() > 10) {
            errorMsg(view, "Select at least 9 photo...!");
            return false;
        }*/
        return true;
    }


}
