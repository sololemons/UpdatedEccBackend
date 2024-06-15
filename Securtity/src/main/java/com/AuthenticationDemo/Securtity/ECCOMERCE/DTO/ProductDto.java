package com.AuthenticationDemo.Securtity.ECCOMERCE.DTO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "ProductDto")
public class ProductDto {


    @Getter
    @Setter
    @Column(name = "ProductName")
    private String productName;
    @Setter
    @Getter
    @Column(name = "description")
    private String description;
    @Getter
    @Setter
    @Column(name = "ImageUrl")
    private String Image_Url;
    @Getter
    @Setter
    @Column(name = "Price")
    private Long price;

    @Getter
    @Setter
    @Column(name = "categoryId")
    private Integer categoryId;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;


    public ProductDto() {

    }









}


