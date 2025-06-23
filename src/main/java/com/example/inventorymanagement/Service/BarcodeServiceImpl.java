package com.example.inventorymanagement.Service;

import com.example.inventorymanagement.model.Barcode;
import com.example.inventorymanagement.model.InventoryItem;
import com.example.inventorymanagement.repository.BarcodeRepository;
import com.example.inventorymanagement.repository.ItemRepository;
import com.example.inventorymanagement.util.BarcodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        String countryCode = "890"; // India
        String manufacturerCode = item.getDistributor().getDistributorCode(); // Distributor code
        String productCode = String.format("%05d", item.getId()); // Ensure 5 digits

        String baseBarcode = countryCode + manufacturerCode + productCode; // Total 12 digits
        int checkDigit = BarcodeUtil.calculateEAN13CheckDigit(baseBarcode);

        String fullBarcode = baseBarcode + checkDigit;

        Barcode barcode = new Barcode(fullBarcode, item);
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
