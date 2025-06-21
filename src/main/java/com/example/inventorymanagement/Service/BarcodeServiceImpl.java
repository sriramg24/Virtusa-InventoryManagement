package com.example.inventorymanagement.Service;

import com.example.inventorymanagement.model.Barcode;
import com.example.inventorymanagement.model.InventoryItem;
import com.example.inventorymanagement.repository.BarcodeRepository;
import com.example.inventorymanagement.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BarcodeServiceImpl implements BarcodeService {

    @Autowired
    private BarcodeRepository barcodeRepository;

    @Autowired
    private ItemRepository inventoryItemRepository;

    @Override
    public Barcode generateBarcodeForItem(Integer itemId) {
        InventoryItem item = inventoryItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("InventoryItem not found"));

        String barcodeValue = "EMART-" + UUID.randomUUID().toString();

        Barcode barcode = new Barcode(barcodeValue, item);
        return barcodeRepository.save(barcode);
    }

    @Override
    public Barcode getBarcodeByCode(String code) {
        return barcodeRepository.findById(code)
                .orElseThrow(() -> new RuntimeException("Barcode not found"));
    }

    @Override
    public Optional<Barcode> getBarcodeByItemId(Integer itemId) {
        return barcodeRepository.findByItemId(itemId);
    }

    @Override
    public List<Barcode> getAllBarcodes() {
        return barcodeRepository.findAll();
    }
}
