package developer.com.mr.olx.ApiInterface;

import developer.com.mr.olx.clsApiClasses.ClsFillProductPost;
import developer.com.mr.olx.clsApiClasses.ClsFillProductPostParams;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface InterfaceMultiPartProduct {

/*
    @Multipart
    @POST("product")
    Call<ClsFillProductPost> PostFillProduct(@Part MultipartBody.Part file,
                                             @Part("title") RequestBody title,
                                             @Part("description") RequestBody description,
                                             @Part("price") RequestBody price,
                                             @Part("category") RequestBody category,
                                             @Part("sold") RequestBody sold,
                                             @Part("_id") RequestBody _id,
                                             @Part("date") RequestBody date,
                                             @Part("__v") RequestBody __v);*/


    @Multipart
    @POST("product")
    Call<ClsFillProductPost> PostFillProduct(@Part MultipartBody.Part file, @Part("title") RequestBody title,
                                             @Part("description") RequestBody description,
                                             @Part("price") RequestBody price,
                                             @Part("category") RequestBody category);


    @Multipart
    @POST("product")
    Call<ClsFillProductPost> PostFillProduct(@Part("title") RequestBody title);


   /* @Headers(
            {"x-auth-token:yJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoiNjIyNzc4MGI2YTBlZjljN2Y0Y2UxM2RmIn0sImlhdCI6MTY0Njc1NTM3NX0.8qIpn2PIh18S7O8ahj1HpUMyl3fZqXVC-sB5vqRw8do",
                    "Content-Type:application/json"
            })*/

    @Headers(
            {"x-auth-token:yJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoiNjIyNzc4MGI2YTBlZjljN2Y0Y2UxM2RmIn0sImlhdCI6MTY0Njc1NTM3NX0.8qIpn2PIh18S7O8ahj1HpUMyl3fZqXVC-sB5vqRw8do"})
    @Multipart
    @POST("product")
    Call<ClsFillProductPost> PostFillProductTest(@Part("title") RequestBody title,
                                                 @Part("category") RequestBody category,
                                                 @Part("price") RequestBody price,
                                                 @Part("description") RequestBody description
    );

}
