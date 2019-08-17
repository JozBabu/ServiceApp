package com.essensol.serviceapp.RetrofitUtilits;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {


    public static String BASE_URL ="http://192.168.1.16:1212/api/";

    public static Retrofit getRetrofit(){

        return new Retrofit.Builder().baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
