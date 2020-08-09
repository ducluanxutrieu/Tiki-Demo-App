package com.ducluanxutrieu.tikiapp.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("sku")
    @Expose
    private Object sku;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url_path")
    @Expose
    private String urlPath;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("list_price")
    @Expose
    private Integer listPrice;
    @SerializedName("discount")
    @Expose
    private Integer discount;
    @SerializedName("rating_average")
    @Expose
    private Integer ratingAverage;
    @SerializedName("review_count")
    @Expose
    private Integer reviewCount;
    @SerializedName("order_count")
    @Expose
    private Integer orderCount;
    @SerializedName("thumbnail_url")
    @Expose
    private String thumbnailUrl;
    @SerializedName("is_visible")
    @Expose
    private Boolean isVisible;
    @SerializedName("is_fresh")
    @Expose
    private Boolean isFresh;
    @SerializedName("is_flower")
    @Expose
    private Boolean isFlower;
    @SerializedName("is_gift_card")
    @Expose
    private Boolean isGiftCard;
    @SerializedName("inventory")
    @Expose
    private ProductInventory productInventory;
    @SerializedName("url_attendant_input_form")
    @Expose
    private String urlAttendantInputForm;
    @SerializedName("master_id")
    @Expose
    private Integer masterId;
    @SerializedName("seller_product_id")
    @Expose
    private Integer sellerProductId;
    @SerializedName("price_prefix")
    @Expose
    private String pricePrefix;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getSku() {
        return sku;
    }

    public void setSku(Object sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getListPrice() {
        return listPrice;
    }

    public void setListPrice(Integer listPrice) {
        this.listPrice = listPrice;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getRatingAverage() {
        return ratingAverage;
    }

    public void setRatingAverage(Integer ratingAverage) {
        this.ratingAverage = ratingAverage;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Boolean getIsFresh() {
        return isFresh;
    }

    public void setIsFresh(Boolean isFresh) {
        this.isFresh = isFresh;
    }

    public Boolean getIsFlower() {
        return isFlower;
    }

    public void setIsFlower(Boolean isFlower) {
        this.isFlower = isFlower;
    }

    public Boolean getIsGiftCard() {
        return isGiftCard;
    }

    public void setIsGiftCard(Boolean isGiftCard) {
        this.isGiftCard = isGiftCard;
    }

    public ProductInventory getProductInventory() {
        return productInventory;
    }

    public void setProductInventory(ProductInventory productInventory) {
        this.productInventory = productInventory;
    }

    public String getUrlAttendantInputForm() {
        return urlAttendantInputForm;
    }

    public void setUrlAttendantInputForm(String urlAttendantInputForm) {
        this.urlAttendantInputForm = urlAttendantInputForm;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public Integer getSellerProductId() {
        return sellerProductId;
    }

    public void setSellerProductId(Integer sellerProductId) {
        this.sellerProductId = sellerProductId;
    }

    public String getPricePrefix() {
        return pricePrefix;
    }

    public void setPricePrefix(String pricePrefix) {
        this.pricePrefix = pricePrefix;
    }

}