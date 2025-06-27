package com.example.inventorymanagement.Service;

import com.example.inventorymanagement.model.Outlet;
import com.example.inventorymanagement.repository.OutletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutletService {

    @Autowired
    private OutletRepository outletRepository;

    // Create or update outlet
    public Outlet saveOutlet(Outlet outlet) {
        return outletRepository.save(outlet);
    }

    // Get all outlets
    public List<Outlet> getAllOutlets() {
        return outletRepository.findAll();
    }

    // Get outlet by ID
    public Optional<Outlet> getOutletById(Long id) {
        return outletRepository.findById(id);
    }

    // Update outlet by ID
    public Optional<Outlet> updateOutlet(Long id, Outlet updatedOutlet) {
        return outletRepository.findById(id).map(outlet -> {
            outlet.setName(updatedOutlet.getName());
            outlet.setLocation(updatedOutlet.getLocation());
            outlet.setContactNumber(updatedOutlet.getContactNumber());
            outlet.setManagerName(updatedOutlet.getManagerName());
            return outletRepository.save(outlet);
        });
    }

    // Delete outlet by ID
    public void deleteOutlet(Long id) {
        outletRepository.deleteById(id);
    }
}
