package com.essensol.serviceapp.Model_Classes;

public class AccessoriesList_model {

    String AccessoryId,AccessoryName;

    public AccessoriesList_model(String accessoryId, String accessoryName) {
        AccessoryId = accessoryId;
        AccessoryName = accessoryName;
    }

    public String getAccessoryId() {
        return AccessoryId;
    }

    public void setAccessoryId(String accessoryId) {
        AccessoryId = accessoryId;
    }

    public String getAccessoryName() {
        return AccessoryName;
    }

    public void setAccessoryName(String accessoryName) {
        AccessoryName = accessoryName;
    }

    @Override
    public String toString() {
        return AccessoryName ;
    }
}


