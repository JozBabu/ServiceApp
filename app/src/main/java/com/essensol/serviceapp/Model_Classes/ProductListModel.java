package com.essensol.serviceapp.Model_Classes;

public class ProductListModel {

        String JobId,Name,Product,CreatedOn;

    public ProductListModel(String jobId, String name, String product, String createdOn) {
        JobId = jobId;
        Name = name;
        Product = product;
        CreatedOn = createdOn;
    }

    public String getJobId() {
        return JobId;
    }

    public void setJobId(String jobId) {
        JobId = jobId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }
}
