package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.DTO.InventoryCSVDTO;
import com.example.inventorymanagement.Service.InventoryServiceImpl;
import com.example.inventorymanagement.util.CSVHelper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class InventoryController {
    @RestController
    @RequestMapping("/api/inventory")
    public class inventoryController {

        @Autowired
        private InventoryServiceImpl inventoryService;

        @PostMapping("/upload")
        public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file) {
            try {
                List<InventoryCSVDTO> items = CSVHelper.convert(file.getInputStream());
                inventoryService.saveInventoryFromCSV(items);
                return ResponseEntity.ok("Uploaded Successfully");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Upload failed: " + e.getMessage());
            }
        }
        @PostConstruct
        public void init() {
            System.out.println("✅ InventoryController loaded");
        }
    }



}
