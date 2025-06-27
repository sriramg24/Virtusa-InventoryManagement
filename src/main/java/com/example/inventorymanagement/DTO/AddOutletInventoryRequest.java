package com.example.inventorymanagement.DTO;

public class AddOutletInventoryRequest {

    private Long outletId;
    private Long inventoryItemId;
    private Integer quantity;

    public AddOutletInventoryRequest() {
    }

    public AddOutletInventoryRequest(Long outletId, Long inventoryItemId, Integer quantity) {
        this.outletId = outletId;
        this.inventoryItemId = inventoryItemId;
        this.quantity = quantity;
    }

    public Long getOutletId() {
        return outletId;
    }

    public void setOutletId(Long outletId) {
        this.outletId = outletId;
    }

    public Long getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(Long inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
