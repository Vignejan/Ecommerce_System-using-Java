package com.example.Ecommerce_Site.Controller;

import com.example.Ecommerce_Site.DTO.VehicleDTO;
import com.example.Ecommerce_Site.Service.Interface_Service.VehicleServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vehicle")
public class VehicleController {

    @Autowired
    private VehicleServiceInt vehicleService;

    @GetMapping()
    public List<VehicleDTO> getVehicles(){
       return vehicleService.getVehicles();
    }
    @GetMapping("/{id}")
    public VehicleDTO getVehicleById(@PathVariable Long id){
        return vehicleService.getVehicleById(id);
    }
    @PostMapping()
    public VehicleDTO addVehicle(@RequestBody VehicleDTO vehicleD){
        return  vehicleService.addVehicle(vehicleD);

    }
    @PutMapping("/{id}")
    public VehicleDTO updateVehicle(@RequestBody VehicleDTO vehicleD, @PathVariable Long id){
       return vehicleService.updateVehicle(vehicleD,id);
    }
    @DeleteMapping("/{id}")
    public String deleteVehicle(@PathVariable Long id){
        vehicleService.deleteVehicle(id);
        return "Vehicle deleted successfully and it's model name:"+vehicleService.getVehicleById(id).getModel();
    }

}

