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

    public Outlet createOutlet(Outlet outlet) {
        return outletRepository.save(outlet);
    }

    public List<Outlet> getAllOutlets() {
        return outletRepository.findAll();
    }

    public Optional<Outlet> getOutletById(Long id) {
        return outletRepository.findById(id);
    }

    public Outlet updateOutlet(Long id, Outlet updatedOutlet) {
        Outlet outlet = outletRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Outlet not found"));

        outlet.setName(updatedOutlet.getName());
        outlet.setLocation(updatedOutlet.getLocation());
        outlet.setContactNumber(updatedOutlet.getContactNumber());
        outlet.setManagerName(updatedOutlet.getManagerName());

        return outletRepository.save(outlet);
    }

    public void deleteOutlet(Long id) {
        outletRepository.deleteById(id);
    }
}
