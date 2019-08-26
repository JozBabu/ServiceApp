package com.essensol.serviceapp.RetroftResponseClasses;

import com.essensol.serviceapp.Utility._CONSTANTS;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServiceDetailsResponse {

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

        @SerializedName(_CONSTANTS.JobNo)
        @Expose
        private String JobNo;

        @SerializedName(_CONSTANTS.ServiceDate)
        @Expose
        private String ServiceDate;

        @SerializedName(_CONSTANTS.ProblemDetails)
        @Expose
        private String ProblemDetails;

        @SerializedName(_CONSTANTS.CustomerName)
        @Expose
        private String CustomerName;

        @SerializedName(_CONSTANTS.AssignDate)
        @Expose
        private String AssignDate;

        @SerializedName(_CONSTANTS.Address)
        @Expose
        private String Address;

        @SerializedName(_CONSTANTS.ContactNo)
        @Expose
        private String ContactNo;

        @SerializedName(_CONSTANTS.EmailID)
        @Expose
        private String EmailID;

        @SerializedName(_CONSTANTS.JobStatus)
        @Expose
        private String JobStatus;



        public String getJobNo() {
            return JobNo;
        }

        public String getServiceDate() {
            return ServiceDate;
        }

        public String getProblemDetails() {
            return ProblemDetails;
        }

        public String getCustomerName() {
            return CustomerName;
        }

        public String getAddress() {
            return Address;
        }

        public String getContactNo() {
            return ContactNo;
        }

        public String getEmailID() {
            return EmailID;
        }

        public String getJobStatus() {
            return JobStatus;
        }

        public String getAssignDate() {
            return AssignDate;
        }
    }


}
