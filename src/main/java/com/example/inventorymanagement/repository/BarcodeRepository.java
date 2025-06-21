package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.model.Barcode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BarcodeRepository extends JpaRepository<Barcode, String> {
    Optional<Barcode> findByItemId(Integer itemId);
}

