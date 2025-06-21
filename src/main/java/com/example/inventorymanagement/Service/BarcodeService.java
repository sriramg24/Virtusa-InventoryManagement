package com.example.inventorymanagement.Service;


import com.example.inventorymanagement.model.Barcode;

import java.util.List;
import java.util.Optional;

public interface BarcodeService {
    Barcode generateBarcodeForItem(Integer itemId);
    Barcode getBarcodeByCode(String code);
    Optional<Barcode> getBarcodeByItemId(Integer itemId);
    List<Barcode> getAllBarcodes();
}

