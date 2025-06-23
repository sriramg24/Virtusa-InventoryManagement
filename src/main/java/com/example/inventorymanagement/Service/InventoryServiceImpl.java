package com.example.inventorymanagement.Service;

import com.example.inventorymanagement.DTO.InventoryCSVDTO;
import com.example.inventorymanagement.model.Category;
import com.example.inventorymanagement.model.Distributor;
import com.example.inventorymanagement.model.InventoryItem;
import com.example.inventorymanagement.repository.CategoryRepository;
import com.example.inventorymanagement.repository.DistributorRepository;
import com.example.inventorymanagement.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class InventoryServiceImpl {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private DistributorRepository distributorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public void saveInventoryFromCSV(List<InventoryCSVDTO> csvItems) {
        for (InventoryCSVDTO dto : csvItems) {
            InventoryItem item = new InventoryItem();

            item.setId(dto.getId());
            item.setName(dto.getProductDescription());
            item.setProductdescription(dto.getProductDescription());
            item.setQuantity(dto.getCount());
            item.setCost(dto.getCost());
            item.setCurrency(dto.getCurrency());

            try {
                Date expiry = new SimpleDateFormat("yyyy-MM-dd").parse(dto.getProductExpiry());
                item.setExpiration(expiry);
            } catch (Exception e) {
                throw new RuntimeException("Invalid date format for productExpiry: " + dto.getProductExpiry());
            }

            Distributor distributor = distributorRepository.findByDistributorCode(dto.getVendorCode())
                    .orElseThrow(() -> new RuntimeException("Distributor not found: " + dto.getVendorCode()));
            item.setDistributor(distributor);

            Category category = categoryRepository.findByName(dto.getCategoryName())
                    .orElseThrow(() -> new RuntimeException("Category not found: " + dto.getCategoryName()));
            item.setCategory(category);

            item.setStatus("ACTIVE");

            itemRepository.save(item);
        }
    }
}
