package developer.com.mr.olx.ApiInterface;

import developer.com.mr.olx.clsApiClasses.ClsBikeScooter;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterfaceBikeScooter {

    @POST("BikeInventoryMast/bike_inventory_mast_save")
    Call<ClsBikeScooter> postBikeScooter(@Body ClsBikeScooter obj);

}
