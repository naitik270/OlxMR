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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

import developer.com.mr.olx.Interfaces.ItemClickListener;
import developer.com.mr.olx.ModelClasses.MultipartJobFormAPI;
import developer.com.mr.olx.ModelClasses.clsPhoto;
import developer.com.mr.olx.R;
import developer.com.mr.olx.adapter.UriAdapter;
import developer.com.mr.olx.cropClass.CropActivity;
import developer.com.mr.olx.global.ClsGlobal;
import developer.com.mr.olx.matisse.Matisse;
import developer.com.mr.olx.productsList.ClsInventoryMasterFormData;

public class JobFormActivity extends AppCompatActivity {
    String sub_title = "";
    TextView txt_title;
    Button btn_post;
    EditText edt_title, edt_from_salary, edt_to_salary, edt_designation, edt_description, edt_location;
    TextView txt_select_job_type, txt_select_salary_period, txt_select_position;

    private RadioButton rb_mobile_yes, rb_mobile_no;
    String getMobileNoVal = "";
    boolean getMobileNoValBool;
    int getMobileNoValInt;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_form);

        sub_title = getIntent().getStringExtra("sub_title");
        cat_id = getIntent().getIntExtra("cat_id", 0);
        sub_id = getIntent().getIntExtra("sub_id", 0);

        txt_title = findViewById(R.id.txt_title);
        txt_title.setText("Add " + sub_title + " details");

        edt_description = findViewById(R.id.edt_description);
        btn_post = findViewById(R.id.btn_post);
        edt_location = findViewById(R.id.edt_location);
        edt_title = findViewById(R.id.edt_title);
        edt_from_salary = findViewById(R.id.edt_from_salary);
        edt_to_salary = findViewById(R.id.edt_to_salary);
        edt_designation = findViewById(R.id.edt_designation);
        txt_select_job_type = findViewById(R.id.txt_select_job_type);
        txt_select_salary_period = findViewById(R.id.txt_select_salary_period);
        txt_select_position = findViewById(R.id.txt_select_position);

        RadioGroup rg_mobile_no_group = findViewById(R.id.rg_mobile_no_group);
        rb_mobile_yes = findViewById(R.id.rb_mobile_yes);
        rb_mobile_no = findViewById(R.id.rb_mobile_no);

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

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        iv_add_image = findViewById(R.id.iv_add_image);
        btn_post = findViewById(R.id.btn_post);

        iv_add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listPhoto.size() != 0 && listPhoto.size() > 10) {
                    ClsGlobal.errorMsg(JobFormActivity.this, v, "Select at least 10 photo...!");
                    return;
                }
                ClsGlobal.OnImageSelection(JobFormActivity.this, REQUEST_CODE_CHOOSE);
            }
        });

        mAdapter = new UriAdapter(JobFormActivity.this, listPhoto);
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

        txt_select_job_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_post.setOnClickListener(view -> {
            Log.e("--Mobile--", "size: " + listPhoto.size());
            if (validation(view)) {
                try {
                    callNewBtnClick();
                } catch (Exception e) {
                    Log.e("--Mobile--", "Exception: " + e.getMessage());
                }
            } else {
                Toast.makeText(JobFormActivity.this, "Fill required value...!", Toast.LENGTH_SHORT).show();
            }
        });
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
                    listPhoto.add(new clsPhoto(i3, selectedPhotos.get(i3),
                            BitmapFactory.decodeFile(selectedPhotos.get(i3))));
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


    private Boolean validation(View view) {

        if (edt_title.getText() == null || edt_title.getText().toString().trim().isEmpty()) {
            ClsGlobal.errorMsg(this, view, "Title is required...!");
            edt_title.requestFocus();
            return false;
        }

        if (edt_from_salary.getText() == null || edt_from_salary.getText().toString().trim().isEmpty()) {
            ClsGlobal.errorMsg(this, view, "Salary is required...!");
            edt_from_salary.requestFocus();
            return false;
        }
        if (edt_to_salary.getText() == null || edt_to_salary.getText().toString().trim().isEmpty()) {
            ClsGlobal.errorMsg(this, view, "Salary is required...!");
            edt_to_salary.requestFocus();
            return false;
        }
      /*  if (txt_select_job_type.getText() == null || txt_select_job_type.getText().toString().trim().isEmpty()) {
            clsGlobal.errorMsg(this, view, "Type is required...!");
            txt_select_job_type.requestFocus();
            return false;
        }*/
       /* if (listPhoto.size() != 0 && listPhoto.size() > 10) {
            errorMsg(view, "Select at least 9 photo...!");
            return false;
        }*/
        return true;
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

        new Handler(Looper.getMainLooper()).postDelayed(() -> uploadMultipleImageAPI(new File(file1), new File(file2), new File(file3), new File(file4)
                , new File(file5), new File(file6), new File(file7), new File(file8), new File(file9), new File(file10)), 2000);
    }

    void saveMultipleImages() {
        ArrayList<Bitmap> bmpArray = new ArrayList<>();
        pd = ClsGlobal._prProgressDialog(JobFormActivity.this,
                "please wait for upload images...!", false);
        pd.show();
        for (int i3 = 0; i3 < this.listPhoto.size(); i3++) {
            bmpArray.add(listPhoto.get(i3).getmBitmap());
        }
        ClsGlobal.storeMultipleImage(JobFormActivity.this, bmpArray);
    }


    void uploadMultipleImageAPI(File mFile1, File mFile2, File mFile3, File mFile4, File mFile5,
                                File mFile6, File mFile7, File mFile8, File mFile9, File mFile10) {

        MultipartJobFormAPI multipleImageUploader = new MultipartJobFormAPI(JobFormActivity.this);
        multipleImageUploader.uploadFormDataFormAPI(0, 1, edt_title.getText().toString(),
                Double.parseDouble(edt_from_salary.getText().toString()), Double.parseDouble(edt_to_salary.getText().toString()),
                mFile1, mFile2, mFile3, mFile4, mFile5, mFile6, mFile7, mFile8, mFile9, mFile10,
                Integer.parseInt(txt_select_job_type.getTag().toString()), Integer.parseInt(txt_select_salary_period.getTag().toString()),
                Integer.parseInt(txt_select_position.getTag().toString()), edt_designation.getText().toString(),
                getMobileNoValInt, edt_description.getText().toString(), edt_location.getText().toString(),
                Double.parseDouble("12.2365"), Double.parseDouble("78.2558"), cat_id, sub_id);

        multipleImageUploader.showUploadProgressBar(false);
        multipleImageUploader.SetCallBack(new MultipartJobFormAPI.FileUploaderCallback() {
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
                    ClsGlobal.deleteMultipleImagesFromFolder(JobFormActivity.this);
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
