package com.essensol.serviceapp.RetroftResponseClasses;

import com.essensol.serviceapp.Utility._CONSTANTS;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDeliveryDetailsResponse {

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
        @SerializedName(_CONSTANTS.JobId)
        @Expose
        private String JobId;

        @SerializedName(_CONSTANTS.CustomerName)
        @Expose
        private String CustomerName;

        @SerializedName(_CONSTANTS.CustomerAddress)
        @Expose
        private String CustomerAddress;

        @SerializedName(_CONSTANTS.Product)
        @Expose
        private String Product;

        @SerializedName(_CONSTANTS.JobDate)
        @Expose
        private String JobDate;

        public String getJobId() {
            return JobId;
        }

        public String getCustomerName() {
            return CustomerName;
        }

        public String getCustomerAddress() {
            return CustomerAddress;
        }

        public String getProduct() {
            return Product;
        }

        public String getJobDate() {
            return JobDate;
        }
    }


}
