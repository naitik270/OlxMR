package developer.com.mr.olx.ApiInterface;

import developer.com.mr.olx.clsApiClasses.ClsUserMasterFillParams;
import developer.com.mr.olx.clsApiClasses.ClsUserMasterFillResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InterfaceUserMaster {

    @POST("UserMast/user_mast_fill")
    Call<ClsUserMasterFillParams> postUserMasterFill(@Body ClsUserMasterFillParams obj);


    @GET("UserMast/user_mast_fill")
    Call<ClsUserMasterFillParams> getUserMasterFill(@Body ClsUserMasterFillParams obj);


}


