package com.example.inventorymanagement.Service;

import com.example.inventorymanagement.model.InventoryItem;
import com.example.inventorymanagement.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public InventoryItem addItem(InventoryItem item) {
        return itemRepository.save(item);
    }

    public List<InventoryItem> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<InventoryItem> getItemById(Integer id) {
        return itemRepository.findById(id);
    }

    public InventoryItem updateItem(Integer id, InventoryItem updatedItem) {
        return itemRepository.findById(id).map(item -> {
            item.setName(updatedItem.getName());
            item.setCategory(updatedItem.getCategory());
            item.setQuantity(updatedItem.getQuantity());
            item.setExpiration(updatedItem.getExpiration());
            item.setStatus(updatedItem.getStatus());
            return itemRepository.save(item);
        }).orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
    }

    public void deleteItem(Integer id) {
        itemRepository.deleteById(id);
    }
}
