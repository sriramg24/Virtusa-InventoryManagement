package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.model.InventoryItem;
import com.example.inventorymanagement.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // Add new item
    @PostMapping("/add")
    public String addItem(@RequestBody InventoryItem item) {
        itemService.addItem(item);
        return "Item added successfully!";
    }

    // Get all items
    @GetMapping
    public List<InventoryItem> getAllItems() {
        return itemService.getAllItems();
    }

    // Get item by ID
    @GetMapping("/{id}")
    public Optional<InventoryItem> getItemById(@PathVariable Integer id) {
        return itemService.getItemById(id);
    }

    // Get item by barcode


    // Update item
    @PutMapping("/update/{id}")
    public String updateItem(@PathVariable Integer id, @RequestBody InventoryItem item) {
        itemService.updateItem(id, item);
        return "Item updated successfully!";
    }

    // Delete item
    @DeleteMapping("/delete/{id}")
    public String deleteItem(@PathVariable Integer id) {
        itemService.deleteItem(id);
        return "Item deleted successfully!";
    }
}

