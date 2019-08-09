package com.essensol.serviceapp.RetroftResponseClasses;

import com.essensol.serviceapp.Utility._CONSTANTS;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {

    @SerializedName("responseCode")
    @Expose
    private String responseCode;


    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;


    @SerializedName("result")
    @Expose
    private List<Result> result = null;


    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public List<Result> getResult() {
        return result;
    }

    public class Result {

        @SerializedName(_CONSTANTS.LoginResult)
        @Expose
        private String LoginResult;

        @SerializedName(_CONSTANTS.LoginMsg)
        @Expose
        private String LoginMsg;

        @SerializedName(_CONSTANTS.UserId)
        @Expose
        private String UserId;

        @SerializedName(_CONSTANTS.StaffId)
        @Expose
        private String StaffId;

        @SerializedName(_CONSTANTS.StaffName)
        @Expose
        private String StaffName;

        @SerializedName(_CONSTANTS.RoleId)
        @Expose
        private String RoleId;

        @SerializedName(_CONSTANTS.CompID)
        @Expose
        private String CompID;

        @SerializedName(_CONSTANTS.BrId)
        @Expose
        private String BrId;


        public String getLoginResult() {
            return LoginResult;
        }

        public String getLoginMsg() {
            return LoginMsg;
        }

        public String getUserId() {
            return UserId;
        }

        public String getStaffId() {
            return StaffId;
        }

        public String getStaffName() {
            return StaffName;
        }

        public String getRoleId() {
            return RoleId;
        }

        public String getCompID() {
            return CompID;
        }

        public String getBrId() {
            return BrId;
        }
    }


}
