package com.essensol.serviceapp.RetroftResponseClasses;

import com.essensol.serviceapp.Utility._CONSTANTS;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TaskListResponse {

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

        @SerializedName(_CONSTANTS.TaskId)
        @Expose
        private String TaskId;

        @SerializedName(_CONSTANTS.TaskName)
        @Expose
        private String TaskName;

        @SerializedName(_CONSTANTS.Description)
        @Expose
        private String Description;

        @SerializedName(_CONSTANTS.StatusName)
        @Expose
        private String StatusName;

        @SerializedName(_CONSTANTS.CreatedOn)
        @Expose
        private String CreatedOn;


        public String getTaskId() {
            return TaskId;
        }

        public String getTaskName() {
            return TaskName;
        }

        public String getDescription() {
            return Description;
        }

        public String getStatusName() {
            return StatusName;
        }

        public String getCreatedOn() {
            return CreatedOn;
        }
    }
}
