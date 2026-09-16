package com.example.Ecommerce_Site.Service.Interface_Service;


import com.example.Ecommerce_Site.DTO.VehicleDTO;

import java.util.List;

public interface VehicleServiceInt {
     List<VehicleDTO> getVehicles();
     VehicleDTO getVehicleById(Long id);
     VehicleDTO addVehicle(VehicleDTO vehicleD);
     VehicleDTO updateVehicle(VehicleDTO vehicleD, Long id);
     void deleteVehicle(Long id);
}
