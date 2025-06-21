package com.example.inventorymanagement.controller;


import com.example.inventorymanagement.model.Distributor;
import com.example.inventorymanagement.Service.DistributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/distributors")
@CrossOrigin(origins = "*")
public class DistributorController {

    @Autowired
    private DistributorService distributorService;

    @PostMapping("/add")
    public Distributor addDistributor(@RequestBody Distributor distributor) {
        return distributorService.addDistributor(distributor);
    }

    @GetMapping("/all")
    public List<Distributor> getAllDistributors() {
        return distributorService.getAllDistributors();
    }

    @GetMapping("/{id}")
    public Optional<Distributor> getDistributorById(@PathVariable Long id) {
        return distributorService.getDistributorById(id);
    }

    @PutMapping("/update/{id}")
    public Distributor updateDistributor(@PathVariable Long id, @RequestBody Distributor distributor) {
        return distributorService.updateDistributor(id, distributor);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDistributor(@PathVariable Long id) {
        distributorService.deleteDistributor(id);
    }
}
