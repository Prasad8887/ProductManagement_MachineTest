package com.example.ProductManagement.Controller;

import com.example.ProductManagement.Model.Category;
import com.example.ProductManagement.Service.CategoryService;
import jakarta.persistence.Column;
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
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public Page<Category> GetAllCategory(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                         @RequestParam(value = "size", required = false, defaultValue = "100") int size,
                                         @RequestParam(value = "sort", required = false, defaultValue = "DESC") String sort,
                                         @RequestParam(value = "col", required = false, defaultValue = "categoryId") String col) {

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(sort), col);

        return categoryService.getAllCategory(pageable);
    }

    @GetMapping("/categories/{id}")
    public List<Map> GetCategoryById(@PathVariable("id") Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/categories")
    public List<Map> CreateCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @PutMapping("/categories/{di}")
    public List<Map> SaveOrUpdateUser_Permission(@RequestBody Category category,@PathVariable("di")Long categoryId) {
        category.setCategoryId(categoryId);
        return categoryService.updateCategory(category,categoryId);
    }

    @DeleteMapping("/categories/{id}")
    public List<Map> deleteCategoryById(@PathVariable("id") Long categoryId) {
        return categoryService.deleteCategoryById(categoryId);
    }

}
