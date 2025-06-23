package com.example.inventorymanagement.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "InventoryItem")
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id") // FK in InventoryItem table
    private Category category;

    private Integer quantity;

    @Temporal(TemporalType.DATE)
    private Date expiration;


    private String status;
    @ManyToOne
    @JoinColumn(name = "distributor_id") // Foreign Key in InventoryItem table
    private Distributor distributor;

    public Distributor getDistributor() {
        return distributor;
    }

    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }


    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
