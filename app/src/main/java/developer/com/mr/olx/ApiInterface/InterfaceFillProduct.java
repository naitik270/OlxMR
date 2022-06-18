package developer.com.mr.olx.ApiInterface;

import developer.com.mr.olx.clsApiClasses.ClsFillProductPost;
import developer.com.mr.olx.clsApiClasses.ClsFillProductPostParams;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface InterfaceFillProduct {
    @Multipart
    @POST("product")
    Call<ClsFillProductPostParams> PostFillProduct(@Body ClsFillProductPostParams clsFillProductPost);

}
