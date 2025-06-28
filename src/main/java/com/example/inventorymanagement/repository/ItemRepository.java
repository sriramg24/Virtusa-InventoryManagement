package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<InventoryItem, Integer> {
      List<InventoryItem> findByDistributor(Distributor distributor);

}
