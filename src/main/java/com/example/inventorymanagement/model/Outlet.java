package com.example.inventorymanagement.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Outlet {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String location;

    private String contactNumber;

    private String managerName;
    @JsonBackReference
    @OneToMany(mappedBy = "outlet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OutletInventoryItem> outletInventoryItems = new ArrayList<>();

    public List<OutletInventoryItem> getOutletInventoryItems() {
        return outletInventoryItems;
    }

    public void setOutletInventoryItems(List<OutletInventoryItem> outletInventoryItems) {
        this.outletInventoryItems = outletInventoryItems;
    }


    // Constructors
    public Outlet() {}

    public Outlet(String name, String location, String contactNumber, String managerName) {
        this.name = name;
        this.location = location;
        this.contactNumber = contactNumber;
        this.managerName = managerName;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
}
