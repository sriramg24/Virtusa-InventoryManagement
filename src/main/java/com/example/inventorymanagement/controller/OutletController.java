package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.model.Outlet;
import com.example.inventorymanagement.Service.OutletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/outlets")
@CrossOrigin(origins = "*") // Allow frontend apps to access this API (modify as needed)
public class OutletController {
    @Autowired
    private OutletService outletService;

    @PostMapping
    public ResponseEntity<Outlet> createOutlet(@RequestBody Outlet outlet) {
        Outlet savedOutlet = outletService.saveOutlet(outlet);
        return ResponseEntity.ok(savedOutlet);
    }

    // 📥 Get all outlets
    @GetMapping
    public ResponseEntity<List<Outlet>> getAllOutlets() {
        return ResponseEntity.ok(outletService.getAllOutlets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Outlet> getOutletById(@PathVariable Long id) {
        Optional<Outlet> outlet = outletService.getOutletById(id);
        return outlet.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Outlet> updateOutlet(@PathVariable Long id, @RequestBody Outlet outletDetails) {
        Optional<Outlet> updatedOutlet = outletService.updateOutlet(id, outletDetails);
        return updatedOutlet.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOutlet(@PathVariable Long id) {
        outletService.deleteOutlet(id);
        return ResponseEntity.noContent().build();
    }
}
