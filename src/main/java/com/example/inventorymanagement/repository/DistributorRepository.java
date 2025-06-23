package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.model.Distributor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistributorRepository extends JpaRepository<Distributor, Long> {
    boolean existsByDistributorCode(String distributorCode);
    Optional<Distributor> findByDistributorCode(String distributorCode);
}

