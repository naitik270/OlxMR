package developer.com.mr.olx.ApiInterface;

import developer.com.mr.olx.clsApiClasses.ClsNearLocationMastResponse;
import developer.com.mr.olx.clsApiClasses.ClsNearLocationParams;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface InterfaceNearLocationMast {

    @GET("NearLocationMast/near_location_show")
    Call<ClsNearLocationMastResponse> getNearLocationList();


    @POST("NearLocationMast/near_location_show")
    Call<ClsNearLocationParams> getNearLocationListPost(@Body ClsNearLocationParams obj);
}
