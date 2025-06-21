package com.example.inventorymanagement.model;


import jakarta.persistence.*;

@Entity
@Table(name = "distributors")
public class Distributor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String distributorCode;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String contactPerson;
    private boolean isActive;

    public Distributor() {}

    public Distributor(String distributorCode, String name, String email, String phone, String address, String contactPerson) {
        this.distributorCode = distributorCode;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.contactPerson = contactPerson;
        this.isActive = true;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDistributorCode() { return distributorCode; }
    public void setDistributorCode(String distributorCode) { this.distributorCode = distributorCode; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
}
