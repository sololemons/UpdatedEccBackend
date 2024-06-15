package com.AuthenticationDemo.Securtity.ECCOMERCE.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "CartId")
    Long Cart_Id;

    @Column(name = "Quantity")
    @Getter
    @Setter
    Integer Quantity;

@ManyToOne
@JoinColumn(name = "productId")
@Getter
@Setter
  private Product product;

}
