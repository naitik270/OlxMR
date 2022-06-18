package developer.com.mr.olx.ApiInterface;

import developer.com.mr.olx.clsApiClasses.ClsRentProperty;
import developer.com.mr.olx.clsApiClasses.ClsOffice;
import developer.com.mr.olx.clsApiClasses.ClsSellProperty;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterfaceProperty {

    @POST("PropertyInventorySellMast/property_inventory_sell_mast_save")
    Call<ClsSellProperty> postSellProperty(@Body ClsSellProperty obj);

    @POST("PropertyInventoryRentMast/property_inventory_rent_mast_save")
    Call<ClsRentProperty> postRentProperty(@Body ClsRentProperty obj);

    @POST("OfficeInventoryMast/office_inventory_mast_save")
    Call<ClsOffice> postOffice(@Body ClsOffice obj);

}


