package com.essensol.serviceapp.RetrofitUtilits;

import com.essensol.serviceapp.RetroftResponseClasses.CompletedServiceResponse;
import com.essensol.serviceapp.RetroftResponseClasses.HomeResponse;
import com.essensol.serviceapp.RetroftResponseClasses.InsertMeterRedingResponse;
import com.essensol.serviceapp.RetroftResponseClasses.LoginResponse;
import com.essensol.serviceapp.RetroftResponseClasses.PendingServiceResponse;
import com.essensol.serviceapp.RetroftResponseClasses.ProfileResponse;
import com.essensol.serviceapp.RetroftResponseClasses.VehicleNoResponse;
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
    Call<HomeResponse>Home(@Field(_CONSTANTS.StaffId)String StaffId);



    //Profile
    @POST("CommonApi/GetProfileDetails")
    @FormUrlEncoded
    Call<ProfileResponse>Profile(@Field(_CONSTANTS.StaffId)String StaffId);


    //Pending Service List
    @POST("STPPApi/GetPendingServiceListByStaffId")
    @FormUrlEncoded
    Call<PendingServiceResponse>PendingServiceList(@Field(_CONSTANTS.StaffId)String StaffId);

    //Completed Service List
    @POST("STPPApi/GetCompletedServiceListByStaffId")
    @FormUrlEncoded
    Call<CompletedServiceResponse>CompltedServicelist(@Field(_CONSTANTS.StaffId)String StaffId);

    //Vehicle No Spinner Loading
    @POST("CommonApi/GetVehicleIdNumber")
    Call<VehicleNoResponse>VehcileNo();

    //Save Vehicle Km
    @POST("CommonApi/CheckloginDetails")
    @FormUrlEncoded
    Call<InsertMeterRedingResponse>InsertmeterReading(@Field(_CONSTANTS.StaffId)String StaffId,
                                                      @Field(_CONSTANTS.UserId)String UserId,
                                                      @Field(_CONSTANTS.VehicleId)String VehicleId,
                                                      @Field(_CONSTANTS.MeterReading)String MeterReading,
                                                      @Field(_CONSTANTS.Type)String Type);



}
