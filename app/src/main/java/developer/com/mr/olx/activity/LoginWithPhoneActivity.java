package developer.com.mr.olx.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import developer.com.mr.olx.R;
import developer.com.mr.olx.clsApiClasses.ClsLoginVerifiedOTPParams;
import developer.com.mr.olx.clsApiClasses.ClsLoginWithPhoneParams;
import developer.com.mr.olx.clsApiClasses.ClsLoginWithPhoneResponse;
import developer.com.mr.olx.global.ClsGlobal;
import developer.com.mr.olx.global.ClsPreferencesInfo;
import developer.com.mr.olx.productsList.ApiPostViewModel;

public class LoginWithPhoneActivity extends AppCompatActivity {

    Button btn_get_otp;
    ImageView iv_back;
    private ApiPostViewModel apiPostViewModel;
    private ProgressDialog pd;
    TextView txt_code;
    EditText edt_mobile;
    ClsPreferencesInfo obj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_phone);
        apiPostViewModel = new ViewModelProvider(this).get(ApiPostViewModel.class);
        obj = new ClsPreferencesInfo();
        edt_mobile = findViewById(R.id.edt_mobile);
        txt_code = findViewById(R.id.txt_code);
        btn_get_otp = findViewById(R.id.btn_get_otp);
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(view -> finish());

        btn_get_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation(view)) {
                    try {
                        callNewBtnClick();
                    } catch (Exception e) {
                        Log.e("--Mobile--", "Exception: " + e.getMessage());
                    }
                } else {
                    Toast.makeText(LoginWithPhoneActivity.this, "Fill required value...!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void callNewBtnClick() {
        pd = ClsGlobal._prProgressDialog(this, "Please wait for send OTP...!", false);
        pd.show();
        ClsLoginWithPhoneParams mObj = new ClsLoginWithPhoneParams();
        mObj.setMobile_cc_code(txt_code.getText().toString());
        mObj.setMobile_no(edt_mobile.getText().toString().trim());
        apiPostViewModel.UserMasterOTP(mObj).observe(this, api_obj -> {
            if (api_obj != null) {
                int message = api_obj.getSuccess();
                if (message != 1) {
                    Toast.makeText(this, "Failed to add product...!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "OTP Send Successfully...!", Toast.LENGTH_SHORT).show();
                    for (ClsLoginWithPhoneResponse lst : api_obj.getData()) {
                        obj.setUserID(lst.getLoginId());
                        ClsGlobal.setPreferencesInfo(obj, LoginWithPhoneActivity.this);

                    }
                    startActivity(new Intent(getApplicationContext(), OtpVerificationActivity.class)
                            .putExtra("mobile_no", edt_mobile.getText().toString().trim())
                            .putExtra("cc_code", txt_code.getText().toString()));
                }
            }
            pd.dismiss();
        });
    }

    private Boolean validation(View view) {
        if (edt_mobile.getText() == null || edt_mobile.getText().toString().trim().isEmpty()) {
            ClsGlobal.errorMsg(this, view, "Mobile number is required...!");
            edt_mobile.requestFocus();
            return false;
        } else if (edt_mobile.getText().toString().length() != 10) {
            ClsGlobal.errorMsg(this, view, "10 Digits number required...!");
            edt_mobile.requestFocus();
            return false;
        }
        return true;
    }


}
