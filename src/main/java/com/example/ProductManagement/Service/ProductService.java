package com.example.ProductManagement.Service;

import com.example.ProductManagement.Model.Product;
import com.example.ProductManagement.Repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public Page<Product> getAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }


    public List<Map> getProductById(Long productId) {

        List<Map> resList = new ArrayList<>();
        Map resMap = new LinkedHashMap();
        try {
            Optional<Product> product = productRepository.findById(productId);
            if (product.isPresent()) {
                resMap.put("message", "Product found Successfully......");
                resMap.put("result", product.get());
                resList.add(resMap);
            } else {

                resMap.put("message", "Product not found ......");
                resMap.put("result", null);
                resList.add(resMap);
            }

        } catch (Exception e) {
            resMap.put("message", "FAiled to fetch product......");
            resMap.put("result", null);
            resMap.put("Exception:","Exception Occured");
            resList.add(resMap);
        }
        return resList;
    }


    public List<Map> createProduct(Product product) {
        List<Map> resList = new ArrayList<>();
        Map resMap = new LinkedHashMap();
        try {
            if (product != null) {
                Product productObj = productRepository.save(product);
                resMap.put("message", "Product added Successfully......");
                resMap.put("result", productObj);
                resList.add(resMap);
            } else {
                resMap.put("message", "Failed to add product......");
                resMap.put("result", null);
                resList.add(resMap);
            }
        } catch (Exception e) {
            resMap.put("message", "Failed to Add product......");
            resMap.put("result", null);
            resMap.put("Exception:","Exception Occured");
            resList.add(resMap);
        }
        return resList;
    }


    public List<Map> updateProduct(Product product, Long productId) {
        List<Map> resList = new ArrayList<>();
        Map resMap = new LinkedHashMap();
        try {

            Optional<Product> productFind = productRepository.findById(productId);
            if (productFind.isPresent()) {
                Product p = productFind.get();
                p.setProductId(productId);
                if (product.getProductName() != null && product.getProductName() != "")
                    p.setProductName(product.getProductName());
                if (product.getProductDescription() != null && product.getProductDescription() != "")
                    p.setProductDescription(product.getProductDescription());
                if (product.getProductPrice() > 0)
                    p.setProductPrice(product.getProductPrice());

                Product productObj = productRepository.save(p);
                resMap.put("message", "Product Updated Successfully......");
                resMap.put("result", productObj);
                resList.add(resMap);
            } else {
                resMap.put("message", "Failed to Update product......");
                resMap.put("result", null);
                resList.add(resMap);
            }
        } catch (Exception e) {
            resMap.put("message", "Failed to update product......");
            resMap.put("result", null);
            resMap.put("Exception:","Exception Occured");
            resList.add(resMap);
        }
        return resList;
    }


    public List<Map> deleteProductById(Long productId) {
        List<Map> resList = new ArrayList<>();
        Map resMap = new LinkedHashMap();
        try {

            Optional<Product> product = productRepository.findById(productId);
            if (product.isPresent()) {
                Product cat = product.get();
                productRepository.delete(cat);
                resMap.put("message", "Product deleted Successfully......");
                resMap.put("result", cat);
                resList.add(resMap);

            } else {
                resMap.put("message", "Failed to delete product......");
                resMap.put("result", null);
                resList.add(resMap);
            }
        } catch (Exception e) {
            resMap.put("message", "Failed to update product......");
            resMap.put("result", null);
            resMap.put("exception","Exception Occured");
            resList.add(resMap);
        }
        return resList;
    }

}
