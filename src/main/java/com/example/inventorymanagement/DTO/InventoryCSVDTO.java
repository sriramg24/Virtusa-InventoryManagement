package com.example.inventorymanagement.DTO;

public class InventoryCSVDTO {
    private Integer id;
    private String vendorCode;
    private String categoryName;
    private String productDescription;
    private Integer count;
    private int cost;
    private String currency;
    private String productExpiry;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setProductExpiry(String productExpiry) {
        this.productExpiry = productExpiry;
    }

    public Integer getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public Integer getCount() {
        return count;
    }

    public String getCurrency() {
        return currency;
    }

    public int getCost() {
        return cost;
    }

    public String getProductExpiry() {
        return productExpiry;
    }
}
