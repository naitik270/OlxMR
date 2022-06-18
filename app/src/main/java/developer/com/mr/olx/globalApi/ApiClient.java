package developer.com.mr.olx.globalApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;
    private static Retrofit retrofitNew;

    private static final String BASE_URL = "https://mighty-island-70348.herokuapp.com/api/";
    private static final String TEST_URL = "https://simplifiedcoding.net/demos/";
    private static final String POST_URL = "http://192.168.29.147:8080/api/";
//    private static final String LOCAL_URL = "http://192.168.29.186:3000/api/";
    private static final String LOCAL_URL = "http://13.127.125.36:9901/api/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(POST_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getRequestHeader())
                    .build();
        }
        return retrofit;
    }

   public static Retrofit getRetrofitInstanceGET() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(LOCAL_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getRequestHeaderGET())
                    .build();
        }
        return retrofit;
    }

     public static Retrofit getRetrofitInstanceNewAPI() {
        if (retrofitNew == null) {
            retrofitNew = new Retrofit.Builder()
                    .baseUrl(LOCAL_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getRequestHeader())
                    .build();
        }
        return retrofitNew;
    }

    private static OkHttpClient getRequestHeader() {

        return new OkHttpClient.Builder()
                .callTimeout(30, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(new MyInterceptor())
                .retryOnConnectionFailure(true)
                .build();
    }

    private static OkHttpClient getRequestHeaderGET() {

        return new OkHttpClient.Builder()
                .callTimeout(30, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(new MyInterceptorGET())
                .retryOnConnectionFailure(true)
                .build();
    }

}
