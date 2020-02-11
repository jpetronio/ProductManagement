package com.maybank.exam.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDetailsDTO {

    //Product
    private int productId;

    private String url;

    private String productName;

    private String title;

    private String availability;

    private Double price;

    private String descriptions;

    //Product Category
    private int categoryid;

    private String categoryName;

    //Merchant

    private int merchantId;

    private String merchantName;
}
