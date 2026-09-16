package com.example.Ecommerce_Site.Controller;

import com.example.Ecommerce_Site.Model.Offer;
import com.example.Ecommerce_Site.Service.OfferService;
import com.example.Ecommerce_Site.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offer")
public class OfferController {

    @Autowired
    OfferService offerService;

     @GetMapping
    public List<Offer> getOffers(){
         return offerService.getOffers();
     }

     @GetMapping("/{id}")
     public Offer getOfferById(@PathVariable Long id){
         return offerService.getOfferById(id);
     }

     @PostMapping
     public String addOffer(@RequestBody Offer offer){
          offerService.addOffer(offer);
          return ("Offer added successfully at this id: "+offer.getId());
     }

     @PutMapping("/{id}")
    public String updateOfferByID(@PathVariable Long id, @RequestBody Offer offer){
         offerService.updateOfferByID(id,offer);
         return ("Offer updated successfully for this id: "+ id);
     }

     @DeleteMapping("/clear")
    public String clearOffers(@RequestBody List<Offer> offers){
         offerService.clearOffers(offers);
         return ("Offer deleted successfully "+offers.size());
     }

     @DeleteMapping("/{id}")
    public String deleteOfferByID(@PathVariable Long id){
         offerService.deleteOfferByID(id);
         return ("Offer deleted successfully at this id: "+id);
     }
}
