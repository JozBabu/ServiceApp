package com.essensol.serviceapp.RetroftResponseClasses;

import com.essensol.serviceapp.Utility._CONSTANTS;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProblemListresponse {

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


        @SerializedName(_CONSTANTS.ProblemId)
        @Expose
        private String ProblemId;

        @SerializedName(_CONSTANTS.Problem)
        @Expose
        private String Problem;

        public String getProblemId() {
            return ProblemId;
        }

        public String getProblem() {
            return Problem;
        }
    }
}
