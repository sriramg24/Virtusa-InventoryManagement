package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.model.Distributor;
import com.example.inventorymanagement.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<InventoryItem, Integer> {
    List<InventoryItem> findByDistributor(Distributor distributor);
    Optional<InventoryItem> findByName(String name);
}
