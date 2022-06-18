package developer.com.mr.olx.globalApi;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MyInterceptor implements Interceptor {

    String token="";

    @Override
    public Response intercept(Chain chain) throws IOException {

       /* Request request = chain.request().newBuilder()
                .addHeader("Connection",
                        "Keep-Alive").build();*/


        //rewrite the request to add bearer token
        Request request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer "
                        +"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE2NTA0MzcyNjAsImV4cCI6MTY4MTk3MzI2MH0.lyXt0oZ3FW7htraANT-nxA2bf3v4UUUxldVP3WVoBY4")
                .build();

        return chain.proceed(request);
    }
}
