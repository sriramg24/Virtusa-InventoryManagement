package com.example.inventorymanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "authority")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private EAuthority name;

    @JsonIgnore
    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;


    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public EAuthority getName() { return name; }
    public void setName(EAuthority name) { this.name = name; }

    public Set<User> getUsers() { return users; }
    public void setUsers(Set<User> users) { this.users = users; }
}
