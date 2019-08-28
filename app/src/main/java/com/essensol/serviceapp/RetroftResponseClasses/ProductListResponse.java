package com.essensol.serviceapp.RetroftResponseClasses;

import com.essensol.serviceapp.Utility._CONSTANTS;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductListResponse {

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

        @SerializedName(_CONSTANTS.ProductId)
        @Expose
        private String ProductId;

        @SerializedName(_CONSTANTS.Product)
        @Expose
        private String Product;

        public String getProductId() {
            return ProductId;
        }

        public String getProduct() {
            return Product;
        }
    }
}
