package developer.com.mr.olx.ApiInterface;

import developer.com.mr.olx.productsList.clsInventoryMaster;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterfaceInventory {

    @POST("All_InventoryMast/all_inventory_mast_save")
    Call<clsInventoryMaster> postAddInventory(@Body clsInventoryMaster obj);

}


