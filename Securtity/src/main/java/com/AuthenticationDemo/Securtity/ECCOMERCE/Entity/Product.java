package com.AuthenticationDemo.Securtity.ECCOMERCE.Entity;



import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ProductModel")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("productId")
    @Column(name = "productId")
    private Integer productId;
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

    public Product(Integer productId, String productName, String description, String image_Url, long price, Category category) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        Image_Url = image_Url;
        Price = price;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", Image_Url='" + Image_Url + '\'' +
                ", Price=" + Price +
                ", category=" + category +
                '}';
    }
}
