package com.essensol.serviceapp.Model_Classes;

public class VehicleNo_model {

    private String  VehicleId,LicenseNo;


    public VehicleNo_model(String vehicleId, String licenseNo) {
        VehicleId = vehicleId;
        LicenseNo = licenseNo;
    }

    public String getVehicleId() {
        return VehicleId;
    }

    public void setVehicleId(String vehicleId) {
        VehicleId = vehicleId;
    }

    public String getLicenseNo() {
        return LicenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        LicenseNo = licenseNo;
    }

    @Override
    public String toString() {
        return LicenseNo ;
    }
}
