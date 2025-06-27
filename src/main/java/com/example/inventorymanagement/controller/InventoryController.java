package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.DTO.InventoryCSVDTO;
import com.example.inventorymanagement.util.CSVHelper;
import com.example.inventorymanagement.Service.InventoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryServiceImpl inventoryService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file) {
        try {
            List<InventoryCSVDTO> items = CSVHelper.convert(file.getInputStream());
            inventoryService.saveInventoryFromCSV(items);
            return ResponseEntity.ok("Upload successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Upload failed: " + e.getMessage());
        }
    }
}
