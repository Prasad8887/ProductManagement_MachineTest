package com.example.ProductManagement.Controller;

import com.example.ProductManagement.Model.Product;
import com.example.ProductManagement.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public Page<Product> GetAllProduct(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                         @RequestParam(value = "size", required = false, defaultValue = "100") int size,
                                         @RequestParam(value = "sort", required = false, defaultValue = "DESC") String sort,
                                         @RequestParam(value = "col", required = false, defaultValue = "productId") String col) {

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(sort), col);

        return productService.getAllProduct(pageable);
    }

    @GetMapping("/products/{id}")
    public List<Map> GetProductById(@PathVariable("id") Long productId) {
        return productService.getProductById(productId);
    }

    @PostMapping("/products")
    public List<Map> CreateProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/products/{id}")
    public List<Map> updateProduct(@RequestBody Product product, @PathVariable("id") Long productId) {
        return productService.updateProduct(product, productId);
    }

    @DeleteMapping("/products/{id}")
    public List<Map> deleteProductById(@PathVariable("id") Long id) {
        return productService.deleteProductById(id);
    }

}
