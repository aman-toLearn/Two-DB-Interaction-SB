package com.aman.model.prod;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MULTI_DB_PRODUCT")
@NoArgsConstructor
@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer pid;

    @Column(length = 25)
    @NonNull
    private String pname;

    @NonNull
    private Double price;

    @NonNull
    private Double qty;

    @NonNull
    private String vendor;




}
