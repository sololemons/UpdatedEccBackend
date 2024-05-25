package com.AuthenticationDemo.Securtity.ECCOMERCE.Entity;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ProductModel")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Integer product_id;
    @Setter
    @Getter
    @Column(name = "productName")
    private String productName;
    @Setter
    @Getter
    @Column(name = "description")
    private String description;
    @Setter
    @Getter
    @Column(name = "ImageUrl")
    private String Image_Url;
    @Setter
    @Getter
    @Column(name = "Price")
    private long Price;
    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "categoryId")
    Category category;


    public Product() {
    }

    public Product(Integer product_id, String productName, String description, String image_Url, long price) {
        this.product_id = product_id;
        this.productName = productName;
        this.description = description;
        Image_Url = image_Url;
        Price = price;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "product_id=" + product_id +
                ", productName=" + productName +
                ", description='" + description + '\'' +
                ", Image_Url='" + Image_Url + '\'' +
                ", Price=" + Price +
                '}';
    }



}
