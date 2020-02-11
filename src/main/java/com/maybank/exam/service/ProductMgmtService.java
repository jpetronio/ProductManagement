package com.maybank.exam.service;

import com.maybank.exam.models.ProductDetailsDTO;

import java.util.List;

public interface ProductMgmtService {

    public String save(ProductDetailsDTO details, String identifier);

    public void deleteById(int id);

    public Boolean findProductById(int id);

    public List<ProductDetailsDTO> findByName(String name);

}
