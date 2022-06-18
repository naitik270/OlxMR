package developer.com.mr.olx.ApiInterface;

import developer.com.mr.olx.clsApiClasses.clsAddProductParams;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface InterfaceAddProduct {

    @POST("product")
    Call<clsAddProductParams> PostFillProduct(@Header("x-auth-token") String authHeader,
                                              @Body clsAddProductParams obj);

}


