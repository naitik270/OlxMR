package developer.com.mr.olx.ApiInterface;

import developer.com.mr.olx.userMaster.ClsUserMaster;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterfaceProfile {

    @POST("UserMast/user_mast_save")
    Call<ClsUserMaster> postProfile(@Body ClsUserMaster obj);

}


