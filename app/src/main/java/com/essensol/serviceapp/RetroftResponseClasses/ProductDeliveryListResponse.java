package com.essensol.serviceapp.RetroftResponseClasses;

import com.essensol.serviceapp.Utility._CONSTANTS;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDeliveryListResponse {

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

        @SerializedName(_CONSTANTS.Name)
        @Expose
        private String Name;

        @SerializedName(_CONSTANTS.Product)
        @Expose
        private String Product;

        @SerializedName(_CONSTANTS.CreatedOn)
        @Expose
        private String CreatedOn;


        public String getJobId() {
            return JobId;
        }

        public String getName() {
            return Name;
        }

        public String getProduct() {
            return Product;
        }

        public String getCreatedOn() {
            return CreatedOn;
        }
    }



}
