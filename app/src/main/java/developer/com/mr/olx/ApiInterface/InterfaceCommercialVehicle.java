package developer.com.mr.olx.ApiInterface;

import developer.com.mr.olx.clsApiClasses.ClsBikeScooter;
import developer.com.mr.olx.clsApiClasses.ClsCommercialVehicleParams;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterfaceCommercialVehicle {

    @POST("CommVehicleInventoryMast/vehicle_inventory_mast_save")
    Call<ClsCommercialVehicleParams> postCommercialVehicle(@Body ClsCommercialVehicleParams obj);

}
