package com.example.Ecommerce_Site.Controller;

import com.example.Ecommerce_Site.Model.Address;
import com.example.Ecommerce_Site.Repository.AddressRepo;
import com.example.Ecommerce_Site.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public List<Address> getAddress(){
       return addressService.getAddress();
    }
    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable Long id){
        return addressService.getAddressById(id);
    }
    @PostMapping
    public String addAddress(@RequestBody Address address){
        addressService.addAddress(address);
        return "Address added successfully";
    }
    @PutMapping("/{id}")
    public String updateAddress(@PathVariable Long id, @RequestBody Address address){
        addressService.updateAddress(id,address);
        return "Address updated Successfully for this Id:"+id;
    }
    @DeleteMapping("/{id}")
    public String deleteAddressById(@PathVariable Long id){
        addressService.deleteAddressById(id);
        return "Address deleted Successfully for this Id:"+id;
    }
}
