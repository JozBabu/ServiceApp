package com.essensol.serviceapp.RetroftResponseClasses;

import com.essensol.serviceapp.Utility._CONSTANTS;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileResponse {

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

        @SerializedName(_CONSTANTS.StaffName)
        @Expose
        private String StaffName;

        @SerializedName(_CONSTANTS.StaffCode)
        @Expose
        private String StaffCode;

        @SerializedName(_CONSTANTS.DesignationName)
        @Expose
        private String DesignationName;

        @SerializedName(_CONSTANTS.ProfileImage)
        @Expose
        private String ProfileImage;

        @SerializedName(_CONSTANTS.MobileNo)
        @Expose
        private String MobileNo;

        @SerializedName(_CONSTANTS.EmailId)
        @Expose
        private String EmailId;

        @SerializedName(_CONSTANTS.Address)
        @Expose
        private String Address;


        public String getStaffName() {
            return StaffName;
        }

        public String getStaffCode() {
            return StaffCode;
        }

        public String getDesignationName() {
            return DesignationName;
        }

        public String getProfileImage() {
            return ProfileImage;
        }

        public String getMobileNo() {
            return MobileNo;
        }

        public String getEmailId() {
            return EmailId;
        }

        public String getAddress() {
            return Address;
        }
    }
}
