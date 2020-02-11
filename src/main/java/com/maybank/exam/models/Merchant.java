package com.maybank.exam.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Merchant {

    @Id
    private int id;

    private String name;

    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL)
    private List<Product> productList;

    public Merchant(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Merchant() {
    }

}
