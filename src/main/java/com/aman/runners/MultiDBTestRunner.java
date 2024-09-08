package com.aman.runners;

import com.aman.model.prod.Product;
import com.aman.model.promotions.Offers;
import com.aman.repository.prod.IProductRepository;
import com.aman.repository.promotions.IOffersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MultiDBTestRunner implements CommandLineRunner {
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IOffersRepository offersRepository;
    @Override
    public void run(String... args) throws Exception {

        //prepare the product object

        Product prod = new Product("Table",5000.0,10.0,"IKEA");
        int idVal = productRepository.save(prod).getPid();
        System.out.println("Product is saved with the id  " + idVal);


        //prepare the offers object

        Offers offer = new Offers("RakashaBandanna","RK-10",30.0F, LocalDateTime.now());
        int idVal1 = offersRepository.save(offer).getOfferId();
        System.out.println("Offer is saved with the id  " + idVal);



    }
}
