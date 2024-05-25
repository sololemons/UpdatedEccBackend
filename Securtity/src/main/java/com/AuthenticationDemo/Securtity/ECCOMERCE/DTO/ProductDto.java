package com.AuthenticationDemo.Securtity.ECCOMERCE.DTO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "ProductDto")
public class ProductDto {


    @Getter
    @Column(name = "ProductName")
    private String productName;
    @Setter
    @Getter
    @Column(name = "description")
    private String description;
    @Getter
    @Column(name = "ImageUrl")
    private String Image_Url;
    @Getter
    @Setter
    @Column(name = "Price")
    private Long price;

    @Getter
    private Integer categoryId;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long product_id;


    public ProductDto() {

    }


    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setImage_Url(String image_Url) {
        Image_Url = image_Url;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }


}


