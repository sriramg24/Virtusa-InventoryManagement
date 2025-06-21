package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.model.Barcode;
import com.example.inventorymanagement.Service.BarcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/barcodes")
public class BarcodeController {

    @Autowired
    private BarcodeService barcodeService;

    @PostMapping("/generate/{itemId}")
    public Barcode generateBarcode(@PathVariable Integer itemId) {
        return barcodeService.generateBarcodeForItem(itemId);
    }

    @GetMapping("/{code}")
    public Barcode getBarcodeByCode(@PathVariable String code) {
        return barcodeService.getBarcodeByCode(code);
    }

    @GetMapping("/item/{itemId}")
    public Optional<Barcode> getBarcodeByItemId(@PathVariable Integer itemId) {
        return barcodeService.getBarcodeByItemId(itemId);
    }

    @GetMapping
    public List<Barcode> getAllBarcodes() {
        return barcodeService.getAllBarcodes();
    }
}
