package developer.com.mr.olx.ApiInterface;


import java.util.List;

import developer.com.mr.olx.productsList.clsProductGet;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface InterfaceProductList {

    @GET("product/list")
    Call<List<clsProductGet>> getProductList(@Header("x-auth-token") String authHeader,
                                             @Query("limit") int limit,
                                             @Query("order") String order);

}

