package com.essensol.serviceapp.RetrofitUtilits;

import com.essensol.serviceapp.RetroftResponseClasses.CompletedServiceResponse;
import com.essensol.serviceapp.RetroftResponseClasses.HomeResponse;
import com.essensol.serviceapp.RetroftResponseClasses.InsertMeterRedingResponse;
import com.essensol.serviceapp.RetroftResponseClasses.JobSignInSignOutResponse;
import com.essensol.serviceapp.RetroftResponseClasses.LoginResponse;
import com.essensol.serviceapp.RetroftResponseClasses.PaymentCollectionListResponse;
import com.essensol.serviceapp.RetroftResponseClasses.PendingServiceResponse;
import com.essensol.serviceapp.RetroftResponseClasses.ProductDeliveryListResponse;
import com.essensol.serviceapp.RetroftResponseClasses.ProfileResponse;
import com.essensol.serviceapp.RetroftResponseClasses.ServiceDetailsResponse;
import com.essensol.serviceapp.RetroftResponseClasses.TaskDetailsResponse;
import com.essensol.serviceapp.RetroftResponseClasses.TaskListResponse;
import com.essensol.serviceapp.RetroftResponseClasses.TaskStatusListresponse;
import com.essensol.serviceapp.RetroftResponseClasses.TaskSubmitResponse;
import com.essensol.serviceapp.RetroftResponseClasses.VehicleNoResponse;
import com.essensol.serviceapp.RetroftResponseClasses.WorkSignInSignOutResponse;
import com.essensol.serviceapp.Utility._CONSTANTS;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api_interface {


//1. Login
    @POST("CommonApi/CheckloginDetails")
    @FormUrlEncoded
    Call<LoginResponse>Login(@Field(_CONSTANTS.UserName)String uname,
                            @Field(_CONSTANTS.Password)String password,
                            @Field(_CONSTANTS.DeviceType)String DeviceType,
                            @Field(_CONSTANTS.AppToken)String AppToken);


//2. Home
    @POST("CommonApi/GetDashboardDetails")
    @FormUrlEncoded
    Call<HomeResponse>Home(@Field(_CONSTANTS.StaffId)String StaffId);



//3. Profile
    @POST("CommonApi/GetProfileDetails")
    @FormUrlEncoded
    Call<ProfileResponse>Profile(@Field(_CONSTANTS.StaffId)String StaffId);


//4. Pending Service List
    @POST("STPPApi/GetPendingServiceListByStaffId")
    @FormUrlEncoded
    Call<PendingServiceResponse>PendingServiceList(@Field(_CONSTANTS.StaffId)String StaffId,@Field(_CONSTANTS.BrId)String BrId);

//5. Completed Service List
    @POST("STPPApi/GetCompletedServiceListByStaffId")
    @FormUrlEncoded
    Call<CompletedServiceResponse>CompltedServicelist(@Field(_CONSTANTS.StaffId)String StaffId,@Field(_CONSTANTS.BrId)String BrId,@Field(_CONSTANTS.FilterBy)String FilterBy);

//6. Vehicle No Spinner Loading
    @POST("CommonApi/GetVehicleIdNumber")
    Call<VehicleNoResponse>VehcileNo();

//7. Save Vehicle Km
    @POST("VehicleApi/InsertMeterReadingDetails")
    @FormUrlEncoded
    Call<InsertMeterRedingResponse>InsertmeterReading(@Field(_CONSTANTS.StaffId)String StaffId,
                                                      @Field(_CONSTANTS.VehicleId)String VehicleId,
                                                      @Field(_CONSTANTS.MeterReading)String MeterReading,
                                                      @Field(_CONSTANTS.EntryType)String EntryType,
                                                      @Field(_CONSTANTS.CreatedBy)String CreatedBy);


//8. SignIn/SignOut
    @POST("STPPApi/WorkSignInSignOut")
    @FormUrlEncoded
    Call<WorkSignInSignOutResponse>WorkSignInSignOut(@Field(_CONSTANTS.StaffId)String StaffId,
                                                     @Field(_CONSTANTS.PunchType)String PunchType,
                                                     @Field(_CONSTANTS.DeviceType)String DeviceType,
                                                     @Field(_CONSTANTS.CreatedBy)String CreatedBy);



//9. Task  List
    @POST("STPPApi/GetTaskListByStaffId")
    @FormUrlEncoded
    Call<TaskListResponse>TaskList(@Field(_CONSTANTS.StaffId)String StaffId);


//10. ProductDelivery  List
    @POST("STPPApi/GetProductDeliveryListByStaffId")
    @FormUrlEncoded
    Call<ProductDeliveryListResponse>ProductDeliveryList(@Field(_CONSTANTS.StaffId)String StaffId);

//11. PaymentCollection  List
    @POST("STPPApi/GetPendingServiceListByStaffId")
    @FormUrlEncoded
    Call<PaymentCollectionListResponse>PaymentCollectionList(@Field(_CONSTANTS.StaffId)String StaffId);


//12. Service Details
    @POST("STPPApi/GetServiceDetailsById")
    @FormUrlEncoded
    Call<ServiceDetailsResponse>ServiceDetails(@Field(_CONSTANTS.StaffId)String StaffId,
                                               @Field(_CONSTANTS.BrId)String BrId,
                                               @Field(_CONSTANTS.ServiceId)String ServiceId);


//13. Service Details
    @POST("STPPApi/GetTaskDetailsByTaskId")
    @FormUrlEncoded
    Call<TaskDetailsResponse>TaskDetails(@Field(_CONSTANTS.TaskId)String TaskId);

//14. JobSignInSignOut
    @POST("STPPApi/GetServiceDetailsById")
    @FormUrlEncoded
    Call<JobSignInSignOutResponse>JobInOut(@Field(_CONSTANTS.StaffId)String StaffId,
                                           @Field(_CONSTANTS.BrId)String BrId,
                                           @Field(_CONSTANTS.ServiceId)double lat);

//15. TaskStatusSpinner
    @POST("STPPApi/GetTaskStatusList")
    Call<TaskStatusListresponse>TaskStatus();

//16.  TaskSubmit
    @POST("STPPApi/UpdateTaskStatusByTaskId")
    @FormUrlEncoded
    Call<TaskSubmitResponse>TaskSubmit(@Field(_CONSTANTS.TaskId)String TaskId,
                                       @Field(_CONSTANTS.StatusId)String StatusId,
                                       @Field(_CONSTANTS.CreatedBy)String CreatedBy);





}