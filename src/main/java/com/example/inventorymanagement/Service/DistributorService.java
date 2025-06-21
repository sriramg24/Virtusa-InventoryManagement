package com.example.inventorymanagement.Service;


import com.example.inventorymanagement.model.Distributor;
import com.example.inventorymanagement.repository.DistributorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistributorService {

    @Autowired
    private DistributorRepository distributorRepository;

    public Distributor addDistributor(Distributor distributor) {
        return distributorRepository.save(distributor);
    }

    public List<Distributor> getAllDistributors() {
        return distributorRepository.findAll();
    }

    public Optional<Distributor> getDistributorById(Long id) {
        return distributorRepository.findById(id);
    }

    public void deleteDistributor(Long id) {
        distributorRepository.deleteById(id);
    }

    public Distributor updateDistributor(Long id, Distributor updatedDistributor) {
        return distributorRepository.findById(id).map(distributor -> {
            distributor.setName(updatedDistributor.getName());
            distributor.setEmail(updatedDistributor.getEmail());
            distributor.setPhone(updatedDistributor.getPhone());
            distributor.setAddress(updatedDistributor.getAddress());
            distributor.setContactPerson(updatedDistributor.getContactPerson());
            distributor.setActive(updatedDistributor.isActive());
            return distributorRepository.save(distributor);
        }).orElse(null);
    }
}
