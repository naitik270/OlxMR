package developer.com.mr.olx.ApiInterface;

import java.util.List;

import developer.com.mr.olx.UserTest.Example;
import developer.com.mr.olx.productsList.UserModal;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceUserPage {

    @GET("users")
    Call<Example> getAllUserList(@Query("page") int page);

}
