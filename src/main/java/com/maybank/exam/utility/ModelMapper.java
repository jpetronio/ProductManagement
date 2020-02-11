package com.maybank.exam.utility;

import com.maybank.exam.models.Product;
import com.maybank.exam.models.ProductDetailsDTO;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {

    public Product toProducts(ProductDetailsDTO dto) {
        Product product = new Product();
        product.setProductId(dto.getProductId());
        product.setProductName(dto.getProductName());
        product.setUrl(dto.getUrl());
        product.setTitle(dto.getTitle());
        product.setAvailability(dto.getAvailability());
        product.setPrice(dto.getPrice());
        product.setDescriptions(dto.getDescriptions());
        return product;
    }

    public ProductDetailsDTO toDTO(Product product) {
        ProductDetailsDTO dto = new ProductDetailsDTO();
        dto.setProductId(product.getProductId());
        dto.setUrl(product.getUrl());
        dto.setProductName(product.getProductName());
        dto.setTitle(product.getTitle());
        dto.setAvailability(product.getAvailability());
        dto.setPrice(product.getPrice());
        dto.setDescriptions(product.getDescriptions());

        //Product Category
        dto.setCategoryid(product.getProductCategory().getId());
        dto.setCategoryName(product.getProductCategory().getName());

        //Merchant

        dto.setMerchantId(product.getMerchant().getId());
        dto.setMerchantName(product.getMerchant().getName());

        return dto;
    }
}
