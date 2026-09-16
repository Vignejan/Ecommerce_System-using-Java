package com.example.Ecommerce_Site.Service;

import com.example.Ecommerce_Site.Entity.Vehicle;
import com.example.Ecommerce_Site.DTO.VehicleDTO;
import com.example.Ecommerce_Site.Repository.VehicleRepo;
import com.example.Ecommerce_Site.Service.Interface_Service.VehicleServiceInt;
import com.example.Ecommerce_Site.config.VehicleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService implements VehicleServiceInt {

    @Autowired
    VehicleRepo vehicleRepo;
    @Autowired
    VehicleMapper vehicleMapper;

    public List<VehicleDTO> getVehicles() {
        if(vehicleRepo.findAll().isEmpty()){
            throw new RuntimeException("No vehicles found.Please add at least one vehicle");
        }
        return vehicleRepo.findAll().stream().map(ve->vehicleMapper.vehicleToVehicleDTO(ve)).collect(Collectors.toList());
    }

    public VehicleDTO getVehicleById(Long id) {

       Vehicle vehicle= vehicleRepo.findById(id).orElseThrow(()->
                new RuntimeException("Vehicle Not Found! at this id:"+id));
       return vehicleMapper.vehicleToVehicleDTO(vehicle);
    }

    public VehicleDTO addVehicle(VehicleDTO vehicleD) {
       Vehicle vehicle=vehicleRepo.save(vehicleMapper.vehicleDTOtoVehicle(vehicleD));
       return vehicleMapper.vehicleToVehicleDTO(vehicle);
    }

    public VehicleDTO updateVehicle(VehicleDTO vehicleD, Long id) {
        Vehicle vehicle=vehicleRepo.findById(id).orElseThrow(()->
                new  RuntimeException("Vehicle Not Found! at this id:"+id));
        vehicle.setModel(vehicleD.getModel());
        vehicle.setDescription(vehicleD.getDescription());
        vehicle.setPrice(vehicleD.getPrice());
        vehicle.setYearStart(vehicleD.getYearStart());
        vehicle.setYearEnd(vehicleD.getYearEnd());
        vehicle.setType(vehicleD.getVehicleType());
        vehicleRepo.save(vehicle);
        return vehicleMapper.vehicleToVehicleDTO(vehicle);
    }

    public void deleteVehicle(Long id) {
        if(vehicleRepo.findById(id).isEmpty()){
            throw new RuntimeException("Vehicle Not Found! at this id:"+id);
        }
        vehicleRepo.deleteById(id);
        System.out.println("Vehicle Deleted Successfully");
    }
}
