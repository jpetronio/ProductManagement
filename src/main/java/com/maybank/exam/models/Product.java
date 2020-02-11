package com.maybank.exam.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
public class Product {

    @Id
    private int productId;

    private String productName;

    private String url;

    private String title;

    private String availability;

    private Double price;

    private String descriptions;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;


}
