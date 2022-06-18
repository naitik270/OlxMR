package developer.com.mr.olx.ApiInterface;

import developer.com.mr.olx.clsApiClasses.ClsShowCategoryDataParams;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterfaceShowAllAdsData {

    @POST("ShowCategoryDataMast/show_category_data")
    Call<ClsShowCategoryDataParams> getShowCategoryDataListPost(@Body ClsShowCategoryDataParams obj);
}
