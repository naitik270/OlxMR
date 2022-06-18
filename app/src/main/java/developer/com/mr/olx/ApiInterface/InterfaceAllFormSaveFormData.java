package developer.com.mr.olx.ApiInterface;

import developer.com.mr.olx.productsList.ClsInventoryMasterFormData;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface InterfaceAllFormSaveFormData {

    @Multipart
    @POST("All_InventoryMast/all_inventory_mast_save")
    Call<ClsInventoryMasterFormData> postAllFormAPI(@Part("all_inventory_id") RequestBody all_inventory_id,
                                                    @Part("user_id") RequestBody user_id,
                                                    @Part("cat_id") RequestBody cat_id,
                                                    @Part("sub_cat_id") RequestBody sub_cat_id,
                                                    @Part("title") RequestBody title,
                                                    @Part("price") RequestBody price,
                                                    @Part MultipartBody.Part picture_link_1,
                                                    @Part MultipartBody.Part picture_link_2,
                                                    @Part MultipartBody.Part picture_link_3,
                                                    @Part MultipartBody.Part picture_link_4,
                                                    @Part MultipartBody.Part picture_link_5,
                                                    @Part MultipartBody.Part picture_link_6,
                                                    @Part MultipartBody.Part picture_link_7,
                                                    @Part MultipartBody.Part picture_link_8,
                                                    @Part MultipartBody.Part picture_link_9,
                                                    @Part MultipartBody.Part picture_link_10,
                                                    @Part("show_mo_no") RequestBody show_mo_no,
                                                    @Part("description") RequestBody description,
                                                    @Part("location") RequestBody location,
                                                    @Part("latitude") RequestBody latitude,
                                                    @Part("longitude") RequestBody longitude);


}
