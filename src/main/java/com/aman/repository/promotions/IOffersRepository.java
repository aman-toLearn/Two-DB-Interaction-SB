package com.aman.repository.promotions;

import com.aman.model.promotions.Offers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOffersRepository extends JpaRepository<Offers,Integer> {
}
