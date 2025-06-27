package com.example.inventorymanagement.Service;

import com.example.inventorymanagement.DTO.InventoryCSVDTO;
import com.example.inventorymanagement.model.*;
import com.example.inventorymanagement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class InventoryServiceImpl {

    @Autowired
    private ItemRepository inventoryRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private DistributorRepository distributorRepo;

    public void saveInventoryFromCSV(List<InventoryCSVDTO> list) {
        for (InventoryCSVDTO dto : list) {
            InventoryItem item = new InventoryItem();

            item.setName(dto.getName());
            item.setQuantity(dto.getQuantity());
            item.setCost(dto.getCost());
            item.setCurrency(dto.getCurrency());
            item.setProductdescription(dto.getProductdescription());
            item.setStatus(dto.getStatus());

            Category category = categoryRepo.findByName(dto.getCategoryName())
                    .orElseThrow(() -> new RuntimeException("Category not found: " + dto.getCategoryName()));
            item.setCategory(category);

            Distributor distributor = distributorRepo.findByDistributorCode(dto.getDistributorCode())
                    .orElseThrow(() -> new RuntimeException("Distributor not found: " + dto.getDistributorCode()));
            item.setDistributor(distributor);

            try {
                Date expiryDate = new SimpleDateFormat("yyyy-MM-dd").parse(dto.getExpiration());
                item.setExpiration(expiryDate);
            } catch (Exception e) {
                throw new RuntimeException("Invalid date: " + dto.getExpiration());
            }

            inventoryRepo.save(item);
        }
    }
}
