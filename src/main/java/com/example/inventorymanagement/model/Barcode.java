package com.example.inventorymanagement.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Barcode {

    @Id
    private String code; // e.g., UUID, UPC, QR string, etc.

    @OneToOne
    @JoinColumn(name = "inventory_item_id", referencedColumnName = "id")
    private InventoryItem item;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public Barcode() {
        this.createdAt = new Date();
    }

    public Barcode(String code, InventoryItem item) {
        this.code = code;
        this.item = item;
        this.createdAt = new Date();
    }

    // Getters & Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public InventoryItem getItem() {
        return item;
    }

    public void setItem(InventoryItem item) {
        this.item = item;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
