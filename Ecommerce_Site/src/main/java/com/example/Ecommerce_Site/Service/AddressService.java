package com.example.Ecommerce_Site.Service;

import com.example.Ecommerce_Site.Model.Address;
import com.example.Ecommerce_Site.Repository.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepo addressRepo;

    public List<Address> getAddress() {
        if(addressRepo.findAll().isEmpty()){
            throw new RuntimeException("Address not found");
        }
        return addressRepo.findAll();
    }

    public Address getAddressById(Long id) {
        return addressRepo.findById(id).orElseThrow(()->
                new RuntimeException("Address not found! for this Id:"+id));
    }

    public void updateAddress(Long id, Address address) {
        if(addressRepo.findById(id).isEmpty()){
            throw new RuntimeException("Address not found! for this Id:"+id);
        }
        addressRepo.save(address);
    }

    public void addAddress(Address address) {
        addressRepo.save(address);
    }

    public void deleteAddressById(Long id) {
        if(addressRepo.findById(id).isEmpty()){
            throw new RuntimeException("Address not found! for this Id:"+id);
        }
        addressRepo.deleteById(id);
    }
}
