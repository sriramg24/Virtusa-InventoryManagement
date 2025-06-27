package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.DTO.AddOutletInventoryRequest;
import com.example.inventorymanagement.DTO.OutletInventoryItemDTO;
import com.example.inventorymanagement.Service.OutletInventoryService;
import com.example.inventorymanagement.model.OutletInventoryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/outlet-inventory")
public class OutletInventoryController {

    @Autowired
    private OutletInventoryService outletInventoryService;

    @PostMapping("/add")
    public ResponseEntity<OutletInventoryItem> addItemToOutlet(@RequestBody AddOutletInventoryRequest request) {
        OutletInventoryItem result = outletInventoryService.addInventoryToOutlet(
                request.getOutletId(), request.getInventoryItemId(), request.getQuantity()
        );
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{outletId}")
    public ResponseEntity<List<OutletInventoryItemDTO>> getInventoryForOutlet(@PathVariable Long outletId) {
        return ResponseEntity.ok(outletInventoryService.getItemsForOutlet(outletId));
    }

}

