package com.AuthenticationDemo.Securtity.ECCOMERCE.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "CartId")
    @JsonProperty("cartId")
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

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @Getter
    @Setter
    private User user;


}
