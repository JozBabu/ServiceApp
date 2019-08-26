package com.essensol.serviceapp.RetroftResponseClasses;

import com.essensol.serviceapp.Utility._CONSTANTS;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TaskDetailsResponse {

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

        @SerializedName(_CONSTANTS.TaskName)
        @Expose
        private String TaskName;

        @SerializedName(_CONSTANTS.TaskDetails)
        @Expose
        private String TaskDetails;

        @SerializedName(_CONSTANTS.TaskDate)
        @Expose
        private String TaskDate;

        @SerializedName(_CONSTANTS.DueDate)
        @Expose
        private String DueDate;

        @SerializedName(_CONSTANTS.DueTime)
        @Expose
        private String DueTime;

        public String getTaskName() {
            return TaskName;
        }

        public String getTaskDetails() {
            return TaskDetails;
        }

        public String getTaskDate() {
            return TaskDate;
        }

        public String getDueDate() {
            return DueDate;
        }

        public String getDueTime() {
            return DueTime;
        }
    }
}
