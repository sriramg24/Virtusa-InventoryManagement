package com.example.inventorymanagement.DTO;

public class OutletInventoryItemDTO {
    private Integer inventoryItemId;
    private String inventoryItemName;
    private Integer quantity;

    public OutletInventoryItemDTO() {}

    public OutletInventoryItemDTO(Integer inventoryItemId, String inventoryItemName, Integer quantity) {
        this.inventoryItemId = inventoryItemId;
        this.inventoryItemName = inventoryItemName;
        this.quantity = quantity;
    }

    public Integer getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(Integer inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

    public String getInventoryItemName() {
        return inventoryItemName;
    }

    public void setInventoryItemName(String inventoryItemName) {
        this.inventoryItemName = inventoryItemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
