package developer.com.mr.olx.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.alimuzaffar.lib.pin.PinEntryEditText;

import developer.com.mr.olx.R;
import developer.com.mr.olx.clsApiClasses.ClsLoginVerifiedOTPParams;
import developer.com.mr.olx.clsApiClasses.ClsLoginWithPhoneParams;
import developer.com.mr.olx.global.ClsGlobal;
import developer.com.mr.olx.productsList.ApiPostViewModel;

public class OtpVerificationActivity extends AppCompatActivity {


    ImageView iv_back;
    Button btn_get_otp;
    PinEntryEditText pinEntryEditText;
    private ApiPostViewModel apiPostViewModel;
    private ProgressDialog pd;
    String cc_code = "", mobile_no = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        apiPostViewModel = new ViewModelProvider(this).get(ApiPostViewModel.class);
        pinEntryEditText = findViewById(R.id.pinEntryEditText);
        mobile_no = getIntent().getStringExtra("mobile_no");
        cc_code = getIntent().getStringExtra("cc_code");
        btn_get_otp = findViewById(R.id.btn_get_otp);
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(view -> finish());


        btn_get_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int length = pinEntryEditText.getText().length();

                if (length == 4) {
                    if (ClsGlobal.isNetworkConnected(OtpVerificationActivity.this)) {
                        verifiedOtpAPI();
                    }
                }
            }
        });
    }

    void verifiedOtpAPI() {
        pd = ClsGlobal._prProgressDialog(this, "Verifying OTP...!", false);
        pd.show();
        ClsLoginVerifiedOTPParams mObj = new ClsLoginVerifiedOTPParams();
        mObj.setOtp(pinEntryEditText.getText().toString());
        mObj.setMobile_no(mobile_no);
        mObj.setMobile_cc_code(cc_code);
        apiPostViewModel.UserVerifiedMasterOTP(mObj).observe(this, obj -> {
            if (obj != null) {
                int message = obj.getSuccess();
                if (message != 1) {
                    Toast.makeText(this, "OTP Verification is failed...!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "OTP Verified Successfully...!", Toast.LENGTH_SHORT).show();
                    Log.d("--UserID--", "UserCode: " + obj.getData());
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            }
            pd.dismiss();
        });
    }
}
