package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.model.OutletInventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutletInventoryItemRepository extends JpaRepository<OutletInventoryItem, Long> {
    List<OutletInventoryItem> findByOutletId(Long outletId);

}
