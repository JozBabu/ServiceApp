package com.essensol.serviceapp.RetrofitUtilits;

import com.essensol.serviceapp.RetroftResponseClasses.HomeResponse;
import com.essensol.serviceapp.RetroftResponseClasses.LoginResponse;
import com.essensol.serviceapp.Utility._CONSTANTS;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api_interface {


//Login
    @POST("CommonApi/CheckloginDetails")
    @FormUrlEncoded
    Call<LoginResponse>Login(@Field(_CONSTANTS.UserName)String uname,
                            @Field(_CONSTANTS.Password)String password,
                            @Field(_CONSTANTS.DeviceType)String DeviceType,
                            @Field(_CONSTANTS.AppToken)String AppToken);



//Home
    @POST("CommonApi/GetDashboardDetails")
    @FormUrlEncoded
    Call<HomeResponse>Home(@Field(_CONSTANTS.UserId)String UserId);





}
