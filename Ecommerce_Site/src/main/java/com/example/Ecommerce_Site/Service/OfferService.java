package com.example.Ecommerce_Site.Service;

import com.example.Ecommerce_Site.Model.Offer;
import com.example.Ecommerce_Site.Repository.OfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    @Autowired
    OfferRepo offerRepo;

    public List<Offer> getOffers() {
       return offerRepo.findAll();
    }

    public void addOffer(Offer offer) {
          offerRepo.save(offer);
    }

    public Offer updateOfferByID(Long id, Offer newOffer) {
        Offer existingOffer = offerRepo.findById(id).orElseThrow(()->
                new RuntimeException("Offer not found with id " + id+ " and cannot be updated"));

        existingOffer.setName(newOffer.getName());
        existingOffer.setDescription(newOffer.getDescription());
        existingOffer.setDiscountPercentage(newOffer.getDiscountPercentage());
        existingOffer.setStartDate(newOffer.getStartDate());
        existingOffer.setEndDate(newOffer.getEndDate());
        existingOffer.setActive(newOffer.isActive());

      return  offerRepo.save(existingOffer);
    }

    public void clearOffers(List<Offer> offers) {
        offerRepo.deleteAll(offers);
    }


    public void deleteOfferByID(Long id) {
        if(!offerRepo.existsById(id)){
            throw new RuntimeException("offer not found at this id:" + id+" and cannot be deleted");
        }
        offerRepo.deleteById(id);

    }

    public Offer getOfferById(Long id) {
        if(!offerRepo.existsById(id)){
            throw new RuntimeException("offer not found at this id:" + id+ " and cannot be deleted");
        }
        return offerRepo.findById(id).get();
    }
}
