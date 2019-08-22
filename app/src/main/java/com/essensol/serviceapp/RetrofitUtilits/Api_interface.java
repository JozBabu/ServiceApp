package com.essensol.serviceapp.RetrofitUtilits;

import com.essensol.serviceapp.RetroftResponseClasses.CompletedServiceResponse;
import com.essensol.serviceapp.RetroftResponseClasses.HomeResponse;
import com.essensol.serviceapp.RetroftResponseClasses.InsertMeterRedingResponse;
import com.essensol.serviceapp.RetroftResponseClasses.LoginResponse;
import com.essensol.serviceapp.RetroftResponseClasses.PaymentCollectionListResponse;
import com.essensol.serviceapp.RetroftResponseClasses.PendingServiceResponse;
import com.essensol.serviceapp.RetroftResponseClasses.ProductDeliveryListResponse;
import com.essensol.serviceapp.RetroftResponseClasses.ProfileResponse;
import com.essensol.serviceapp.RetroftResponseClasses.TaskListResponse;
import com.essensol.serviceapp.RetroftResponseClasses.VehicleNoResponse;
import com.essensol.serviceapp.RetroftResponseClasses.WorkSignInSignOutResponse;
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
    @POST("VehicleApi/InsertMeterReadingDetails")
    @FormUrlEncoded
    Call<InsertMeterRedingResponse>InsertmeterReading(@Field(_CONSTANTS.StaffId)String StaffId,
                                                      @Field(_CONSTANTS.VehicleId)String VehicleId,
                                                      @Field(_CONSTANTS.MeterReading)String MeterReading,
                                                      @Field(_CONSTANTS.EntryType)String EntryType,
                                                      @Field(_CONSTANTS.CreatedBy)String CreatedBy);


    //SignIn/SignOut
    @POST("STPPApi/WorkSignInSignOut")
    @FormUrlEncoded
    Call<WorkSignInSignOutResponse>WorkSignInSignOut(@Field(_CONSTANTS.StaffId)String StaffId,
                                                     @Field(_CONSTANTS.PunchType)String PunchType,
                                                     @Field(_CONSTANTS.DeviceType)String DeviceType,
                                                     @Field(_CONSTANTS.CreatedBy)String CreatedBy);



    //Task  List
    @POST("STPPApi/GetTaskListByStaffId")
    @FormUrlEncoded
    Call<TaskListResponse>TaskList(@Field(_CONSTANTS.StaffId)String StaffId);


    //ProductDelivery  List
    @POST("STPPApi/GetPendingServiceListByStaffId")
    @FormUrlEncoded
    Call<ProductDeliveryListResponse>ProductDeliveryList(@Field(_CONSTANTS.StaffId)String StaffId);

    //PaymentCollection  List
    @POST("STPPApi/GetPendingServiceListByStaffId")
    @FormUrlEncoded
    Call<PaymentCollectionListResponse>PaymentCollectionList(@Field(_CONSTANTS.StaffId)String StaffId);

}
