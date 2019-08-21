package com.essensol.serviceapp.Model_Classes;

public class TaskListModel {

    String TaskId,TaskName,Description,StatusName,CreatedOn;


    public TaskListModel(String taskId, String taskName, String description, String statusName, String createdOn) {
        TaskId = taskId;
        TaskName = taskName;
        Description = description;
        StatusName = statusName;
        CreatedOn = createdOn;
    }


    public String getTaskId() {
        return TaskId;
    }

    public void setTaskId(String taskId) {
        TaskId = taskId;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String statusName) {
        StatusName = statusName;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }
}


