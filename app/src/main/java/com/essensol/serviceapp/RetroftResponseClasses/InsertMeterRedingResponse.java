package com.essensol.serviceapp.RetroftResponseClasses;

import com.essensol.serviceapp.Utility._CONSTANTS;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InsertMeterRedingResponse {

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

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
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

        public void setResult(String result) {
            this.result = result;
        }

        public String getErrorcode() {
            return errorcode;
        }

        public void setErrorcode(String errorcode) {
            this.errorcode = errorcode;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
