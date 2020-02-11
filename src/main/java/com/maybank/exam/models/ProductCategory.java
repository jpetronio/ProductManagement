package com.maybank.exam.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Setter
@Getter
@Entity
public class ProductCategory {

    @Id
    private int id;

    private String name;

    @OneToMany(mappedBy = "productCategory", cascade = CascadeType.ALL)
    private List<Product> productList;

    public ProductCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductCategory() {
    }

}
