package developer.com.mr.olx.ApiInterface;

import developer.com.mr.olx.clsApiClasses.ClsAddCarRent;
import developer.com.mr.olx.clsApiClasses.ClsAddCarSell;
import developer.com.mr.olx.clsApiClasses.ClsAddMobileDetails;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterfaceCarSell {

    @POST("CarSell_InventoryMast/car_sell_inventory_mast_save")
    Call<ClsAddCarSell> postAddCarSell(@Body ClsAddCarSell obj);

    @POST("CarRentInventoryMast/car_rent_inventory_mast_save")
    Call<ClsAddCarRent> postAddCarRent(@Body ClsAddCarRent obj);

}


