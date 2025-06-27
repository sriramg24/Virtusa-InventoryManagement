package com.example.inventorymanagement.Service;

import com.example.inventorymanagement.DTO.OutletInventoryItemDTO;
import com.example.inventorymanagement.model.InventoryItem;
import com.example.inventorymanagement.model.Outlet;
import com.example.inventorymanagement.model.OutletInventoryItem;
import com.example.inventorymanagement.repository.ItemRepository;
import com.example.inventorymanagement.repository.OutletInventoryItemRepository;
import com.example.inventorymanagement.repository.OutletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OutletInventoryService {

    @Autowired
    private OutletRepository outletRepository;

    @Autowired
    private ItemRepository inventoryItemRepository;

    @Autowired
    private OutletInventoryItemRepository outletInventoryItemRepository;

    public OutletInventoryItem addInventoryToOutlet(Long outletId, Long itemId, Integer quantity) {
        Outlet outlet = outletRepository.findById(outletId)
                .orElseThrow(() -> new RuntimeException("Outlet not found"));

        InventoryItem item = inventoryItemRepository.findById(Math.toIntExact(itemId))
                .orElseThrow(() -> new RuntimeException("Inventory item not found"));

        OutletInventoryItem outletInventoryItem = new OutletInventoryItem(outlet, item, quantity);
        return outletInventoryItemRepository.save(outletInventoryItem);
    }

    public List<OutletInventoryItemDTO> getItemsForOutlet(Long outletId) {
        List<OutletInventoryItem> items = outletInventoryItemRepository.findByOutletId(outletId);

        return items.stream()
                .map(item -> new OutletInventoryItemDTO(
                        item.getInventoryItem().getId(),
                        item.getInventoryItem().getName(),
                        item.getQuantity()
                ))
                .collect(Collectors.toList());
    }

}
