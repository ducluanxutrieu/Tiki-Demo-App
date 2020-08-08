package com.ducluanxutrieu.tikiapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductInventory {

    @SerializedName("product_virtual_type")
    @Expose
    private Object productVirtualType;
    @SerializedName("fulfillment_type")
    @Expose
    private String fulfillmentType;

    public Object getProductVirtualType() {
        return productVirtualType;
    }

    public void setProductVirtualType(Object productVirtualType) {
        this.productVirtualType = productVirtualType;
    }

    public String getFulfillmentType() {
        return fulfillmentType;
    }

    public void setFulfillmentType(String fulfillmentType) {
        this.fulfillmentType = fulfillmentType;
    }

}
