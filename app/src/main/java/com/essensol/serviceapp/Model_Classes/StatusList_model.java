package com.essensol.serviceapp.Model_Classes;

public class StatusList_model {

    String StatusId, StatusName;

    public StatusList_model(String statusId, String statusName) {
        StatusId = statusId;
        StatusName = statusName;
    }

    public String getStatusId() {
        return StatusId;
    }

    public void setStatusId(String statusId) {
        StatusId = statusId;
    }

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String statusName) {
        StatusName = statusName;
    }

    @Override
    public String toString() {
        return  StatusName;
    }
}
