package com.essensol.serviceapp.RetroftResponseClasses;

import com.essensol.serviceapp.Utility._CONSTANTS;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WorkSignInSignOutResponse {

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

        @SerializedName(_CONSTANTS.result)
        @Expose
        private String result;

        @SerializedName(_CONSTANTS.errorcode)
        @Expose
        private String errorcode;

        @SerializedName(_CONSTANTS.msg)
        @Expose
        private String msg;


        public String getResult() {
            return result;
        }

        public String getErrorcode() {
            return errorcode;
        }

        public String getMsg() {
            return msg;
        }
    }
}
