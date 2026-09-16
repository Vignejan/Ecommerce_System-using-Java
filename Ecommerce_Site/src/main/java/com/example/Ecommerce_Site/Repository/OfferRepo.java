package com.example.Ecommerce_Site.Repository;

import com.example.Ecommerce_Site.Model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepo extends JpaRepository<Offer,Long> {

}
