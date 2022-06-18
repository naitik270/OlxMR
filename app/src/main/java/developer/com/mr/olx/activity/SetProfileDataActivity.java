package developer.com.mr.olx.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import developer.com.mr.olx.R;
import developer.com.mr.olx.clsApiClasses.ClsLoginVerifiedOTPParams;
import developer.com.mr.olx.clsApiClasses.ClsSubCategories;
import developer.com.mr.olx.clsApiClasses.ClsUserMasterFillParams;
import developer.com.mr.olx.clsApiClasses.ClsUserMasterFillResponse;
import developer.com.mr.olx.global.ClsGlobal;
import developer.com.mr.olx.global.ClsPreferencesInfo;
import developer.com.mr.olx.productsList.ApiPostViewModel;

public class SetProfileDataActivity extends AppCompatActivity {

    LinearLayout ll_edit_profile;
    ImageView iv_set_img;
    Button btn_post_now;
    int SELECT_PICTURE = 200;
    private ApiPostViewModel apiPostViewModel;
    private ProgressDialog pd;
    ClsPreferencesInfo obj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_profile_data);
        apiPostViewModel = new ViewModelProvider(this).get(ApiPostViewModel.class);
        obj = new ClsPreferencesInfo();
        obj = ClsGlobal.getPreferencesInfo(SetProfileDataActivity.this);
        Log.d("--FillAPI--", "UserCode: " + new Gson().toJson(obj));
        iv_set_img = findViewById(R.id.iv_set_img);
        ll_edit_profile = findViewById(R.id.ll_edit_profile);
        btn_post_now = findViewById(R.id.btn_post_now);

        if (ClsGlobal.isNetworkConnected(SetProfileDataActivity.this)) {
//            verifiedUserMasterFillAPI();

            callSubCategoryByIDAPI();
        }

        btn_post_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CongratulationsActivity.class));
            }
        });

        ll_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();
            }
        });
    }

    List<ClsUserMasterFillResponse> lstClsUserMasterFillResponses = new ArrayList<>();

    void callSubCategoryByIDAPI() {
        pd = ClsGlobal._prProgressDialog(this, "Verifying all information...!", false);
        pd.show();
        ClsUserMasterFillParams mObj = new ClsUserMasterFillParams();
        mObj.setLogin_id(obj.getUserID());
        apiPostViewModel.getUserMasterFillByLoginID(mObj).observe(this, lst -> {
            if (lst.getSuccess() == 1) {
                if (lst.getData().size() != 0) {
                    lstClsUserMasterFillResponses = lst.getData();
                    if (lstClsUserMasterFillResponses.size() > 0) {
                        Log.d("--FillAPI--", "UserCode: " + new Gson().toJson(lstClsUserMasterFillResponses));
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


    void verifiedUserMasterFillAPI() {
        pd = ClsGlobal._prProgressDialog(this, "Verifying OTP...!", false);
        pd.show();
        ClsUserMasterFillParams mObj = new ClsUserMasterFillParams();
        mObj.setLogin_id(obj.getUserID());
        Log.d("--FillAPI--", "UserCode: " + obj.getUserID());
        apiPostViewModel.UserMasterFill(mObj).observe(this, api_obj -> {
            if (api_obj != null) {
                int message = api_obj.getSuccess();
                if (message != 1) {
                    Toast.makeText(this, "OTP Verification is failed...!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "OTP Verified Successfully...!", Toast.LENGTH_SHORT).show();
                    Log.d("--FillAPI--", "UserCode: " + new Gson().toJson(api_obj.getData()));
//                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            }
            pd.dismiss();
        });
    }

    void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    iv_set_img.setImageURI(selectedImageUri);
                }
            }
        }
    }
}
