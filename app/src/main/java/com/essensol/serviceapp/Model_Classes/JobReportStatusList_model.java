package com.essensol.serviceapp.Model_Classes;

public class JobReportStatusList_model {

    String JobStatusId,JobStatusName;

    public JobReportStatusList_model(String jobStatusId, String jobStatusName) {
        JobStatusId = jobStatusId;
        JobStatusName = jobStatusName;
    }

    public String getJobStatusId() {
        return JobStatusId;
    }

    public void setJobStatusId(String jobStatusId) {
        JobStatusId = jobStatusId;
    }

    public String getJobStatusName() {
        return JobStatusName;
    }

    public void setJobStatusName(String jobStatusName) {
        JobStatusName = jobStatusName;
    }

    @Override
    public String toString() {
        return  JobStatusName;
    }
}

