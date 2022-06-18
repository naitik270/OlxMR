package developer.com.mr.olx.ApiInterface;


import developer.com.mr.olx.clsApiClasses.ClsCategoriesResponse;
import developer.com.mr.olx.clsApiClasses.ClsSubCategories;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceCategories {

    @GET("CategoryMast/category_mast_fill")
    Call<ClsCategoriesResponse> getCategoryList();

    @GET("SubCategoryMast/sub_category_mast_fill")
    Call<ClsSubCategories> getSubCategories(@Query("cat_id") int cat_id);

}
