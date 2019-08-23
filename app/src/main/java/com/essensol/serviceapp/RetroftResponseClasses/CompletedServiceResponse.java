package com.essensol.serviceapp.RetroftResponseClasses;

import com.essensol.serviceapp.Utility._CONSTANTS;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompletedServiceResponse {

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

        @SerializedName(_CONSTANTS.ServiceId)
        @Expose
        private String ServiceId;

        @SerializedName(_CONSTANTS.ServiceDate)
        @Expose
        private String ServiceDate;


        @SerializedName(_CONSTANTS.CustomerId)
        @Expose
        private String CustomerId;

        @SerializedName(_CONSTANTS.ProblemDetails)
        @Expose
        private String ProblemDetails;

        @SerializedName(_CONSTANTS.CustomerName)
        @Expose
        private String CustomerName;


        public String getServiceId() {
            return ServiceId;
        }

        public String getServiceDate() {
            return ServiceDate;
        }

        public String getCustomerId() {
            return CustomerId;
        }

        public String getProblemDetails() {
            return ProblemDetails;
        }

        public String getCustomerName() {
            return CustomerName;
        }
    }
}
