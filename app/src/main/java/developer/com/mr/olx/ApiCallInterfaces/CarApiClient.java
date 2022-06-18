package developer.com.mr.olx.ApiCallInterfaces;


import developer.com.mr.olx.CarResponseClasses.ResponseCarClasses;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CarApiClient {
    @GET("relevance/v2/search?category=84&facet_limit=100&location=1000001&location_facet_limit=20&platform=web-desktop&size=40&user=177617c8e60x2ac165fa&lang=en")
    Call<ResponseCarClasses> getApit();
}

