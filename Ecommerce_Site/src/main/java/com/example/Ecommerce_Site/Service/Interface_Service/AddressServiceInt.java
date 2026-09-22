package com.example.Ecommerce_Site.Service.Interface_Service;

import com.example.Ecommerce_Site.DTO.ProductDTO;
import com.example.Ecommerce_Site.Model.Address;
import com.example.Ecommerce_Site.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface AddressServiceInt {
    public List<Address> getAddress();
    public Address getAddressById(Long id);
    public void updateAddress(Long id, Address address);
    public void addAddress(Address address);
    public void deleteAddressById(Long id);
}
