package com.essensol.serviceapp.Model_Classes;

public class ProductList_model {

    String ProductId,Product;

    public ProductList_model(String productId, String product) {
        ProductId = productId;
        Product = product;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    @Override
    public String toString() {
        return Product ;
    }
}
