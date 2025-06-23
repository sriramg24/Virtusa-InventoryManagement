package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {}
