package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.model.Authority;
import com.example.inventorymanagement.model.EAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByName(EAuthority name);
}
