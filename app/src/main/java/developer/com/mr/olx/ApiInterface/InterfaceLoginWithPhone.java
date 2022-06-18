package developer.com.mr.olx.ApiInterface;

import developer.com.mr.olx.clsApiClasses.ClsLoginVerifiedOTPParams;
import developer.com.mr.olx.clsApiClasses.ClsLoginWithPhoneParams;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterfaceLoginWithPhone {

    @POST("UserMast/send_otp")
    Call<ClsLoginWithPhoneParams> postUserSendOTP(@Body ClsLoginWithPhoneParams obj);

    @POST("UserMast/verify_otp")
    Call<ClsLoginVerifiedOTPParams> postVerifiedOTP(@Body ClsLoginVerifiedOTPParams obj);

}


