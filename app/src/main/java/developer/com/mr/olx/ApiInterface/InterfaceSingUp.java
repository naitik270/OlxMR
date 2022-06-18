package developer.com.mr.olx.ApiInterface;

import developer.com.mr.olx.clsApiClasses.ClsRegistrationParams;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterfaceSingUp {

    @POST("users")
    Call<ClsRegistrationParams> postRegistration(@Body ClsRegistrationParams objClsDesignationGetSet);

}
