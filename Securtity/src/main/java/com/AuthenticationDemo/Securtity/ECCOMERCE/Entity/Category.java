package com.AuthenticationDemo.Securtity.ECCOMERCE.Entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("categoryId")
    @Column(name = "categoryId")
    private int categoryId;
    @Setter
    @Getter
    @Column(name = "name")
    private String name;
    @Setter
    @Getter
    @Column(name = "description")
    private String description;
    @Setter
    @Getter
    @Column(name = "image_url")
    private String image_Url;

    public Category() {
    }

    public Category(String name, String description, String image_Url) {
        this.name = name;
        this.description = description;
        this.image_Url = image_Url;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image_Url='" + image_Url + '\'' +
                '}';
    }

}
