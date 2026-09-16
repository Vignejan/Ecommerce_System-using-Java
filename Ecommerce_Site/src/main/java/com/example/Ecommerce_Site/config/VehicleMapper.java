package com.example.Ecommerce_Site.config;

import com.example.Ecommerce_Site.Entity.Vehicle;
import com.example.Ecommerce_Site.DTO.VehicleDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class VehicleMapper {

    public VehicleDTO vehicleToVehicleDTO(Vehicle vehicle) {
        VehicleDTO vehicleDTO = new VehicleDTO(
                vehicle.getId(), vehicle.getType(),vehicle.getDescription(),
                vehicle.getModel(),vehicle.getPrice(),vehicle.getYearStart(),
                vehicle.getYearEnd(),vehicle.getProducts().stream().
                map(prodId->prodId.getId()).collect(Collectors.toSet())

        );

        return vehicleDTO;
    }
     public Vehicle vehicleDTOtoVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleDTO.getId());
        if (vehicleDTO.getVehicleType()!=null) {
            vehicle.setType(vehicleDTO.getVehicleType());
        }
        vehicle.setDescription(vehicleDTO.getDescription());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setPrice(vehicleDTO.getPrice());
        vehicle.setYearStart(vehicleDTO.getYearStart());
        vehicle.setYearEnd(vehicleDTO.getYearEnd());

        return vehicle;
    }
}

