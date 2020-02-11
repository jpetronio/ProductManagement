package com.maybank.exam.service.service.impl;

import com.maybank.exam.models.Merchant;
import com.maybank.exam.models.Product;
import com.maybank.exam.models.ProductCategory;
import com.maybank.exam.models.ProductDetailsDTO;
import com.maybank.exam.models.error.BusinessLogicException;
import com.maybank.exam.repository.MerchantRepository;
import com.maybank.exam.repository.ProductCategoryRepository;
import com.maybank.exam.repository.ProductRepository;
import com.maybank.exam.service.ProductMgmtService;
import com.maybank.exam.utility.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductMgmtServiceImpl implements ProductMgmtService {

    private static final Logger logger = LoggerFactory.getLogger(ProductMgmtServiceImpl.class);

    @Autowired
    ModelMapper mapper;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    MerchantRepository merchantRepository;

    @Override
    public String save(ProductDetailsDTO details, String identifier) {
        String response = "";
        try {
            Boolean existing = findProductById(details.getProductId());
            if(existing && "S".equals(identifier)){
                response = "Product Id already existing";
            }else if(!existing && "U".equals(identifier)) {
                response = "Product Id not existing, cannot update";
            }else {
                Merchant merchant = new Merchant(details.getMerchantId(), details.getMerchantName());
                merchantRepository.save(merchant);

                ProductCategory category = new ProductCategory(details.getCategoryid(), details.getCategoryName());
                productCategoryRepository.save(category);

                Product product = mapper.toProducts(details);
                product.setProductCategory(category);
                product.setMerchant(merchant);
                productRepository.save(product);
                return "Saved : " + product.getProductId() + "," + product.getProductName();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new BusinessLogicException("Product Saving Failed.");
        }
        return response;
    }

    @Override
    public void deleteById(int id) {
        logger.info("delete product by id : " + id);
        productRepository.deleteById(id);
    }

    @Override
    public Boolean findProductById(int id) {
        logger.info("find product by by : " + id);
        return productRepository.findById(id).isPresent();
    }

    @Override
    public List<ProductDetailsDTO> findByName(String name) {
        List<Product> products = productRepository.findByProductName(name);
        List<ProductDetailsDTO> dtoList = new ArrayList<>();
        for (Product s : products) {
            ProductDetailsDTO dto = mapper.toDTO(s);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
