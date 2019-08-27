package com.essensol.serviceapp.RetrofitUtilits;

import com.essensol.serviceapp.R;
import com.essensol.serviceapp.Utility._CONSTANTS;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {


    public static String BASE_URL = _CONSTANTS.localUrl;

    public static Retrofit getRetrofit(){

        return new Retrofit.Builder().baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
