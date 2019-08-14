package com.essensol.serviceapp.Model_Classes;

public class CompletedServiceModel {

    private String ServiceId,ServiceDate,CustomerId,ProblemDetails,CustomerName;

    public CompletedServiceModel(String serviceId, String serviceDate, String customerId, String problemDetails, String customerName) {
        ServiceId = serviceId;
        ServiceDate = serviceDate;
        CustomerId = customerId;
        ProblemDetails = problemDetails;
        CustomerName = customerName;
       }

    public String getServiceId() {
        return ServiceId;
    }

    public void setServiceId(String serviceId) {
        ServiceId = serviceId;
    }

    public String getServiceDate() {
        return ServiceDate;
    }

    public void setServiceDate(String serviceDate) {
        ServiceDate = serviceDate;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getProblemDetails() {
        return ProblemDetails;
    }

    public void setProblemDetails(String problemDetails) {
        ProblemDetails = problemDetails;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }
}
