package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.model.Outlet;
import com.example.inventorymanagement.Service.OutletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/outlets")
public class OutletController {

    @Autowired
    private OutletService outletService;

    @PostMapping("/add")
    public ResponseEntity<Outlet> addOutlet(@RequestBody Outlet outlet) {
        return ResponseEntity.ok(outletService.createOutlet(outlet));
    }

    @GetMapping
    public ResponseEntity<List<Outlet>> getAllOutlets() {
        return ResponseEntity.ok(outletService.getAllOutlets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Outlet> getOutletById(@PathVariable Long id) {
        return outletService.getOutletById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Outlet> updateOutlet(@PathVariable Long id, @RequestBody Outlet outlet) {
        return ResponseEntity.ok(outletService.updateOutlet(id, outlet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOutlet(@PathVariable Long id) {
        outletService.deleteOutlet(id);
        return ResponseEntity.noContent().build();
    }
}

