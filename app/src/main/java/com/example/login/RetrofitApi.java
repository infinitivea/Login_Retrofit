package com.example.login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitApi {

    @FormUrlEncoded
    @POST("loginAPI.php")
    Call<LoginResponse> login(@Field("username") String username, @Field("password") String password);

    @GET("deviceData.php")
    Call<String> getDeviceName();

}
