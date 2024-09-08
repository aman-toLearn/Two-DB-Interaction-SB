package com.aman.model.promotions;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "MULTI_DB_OFFERS")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Offers {

    @Id
    @SequenceGenerator(name = "gen1",sequenceName = "offer_seq",initialValue = 1000,
    allocationSize = 1)
    @GeneratedValue(generator = "gen1",strategy = GenerationType.SEQUENCE)

    private Integer offerId;

    @Column(length = 20)
    @NonNull
    private String offerName;

    @Column(length = 20)
    @NonNull
    private String offerCode;


    @NonNull
    private Float discountPercentage;
    @NonNull
    private LocalDateTime expiryDate;



}
