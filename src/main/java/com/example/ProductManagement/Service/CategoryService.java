package com.example.ProductManagement.Service;

import com.example.ProductManagement.Model.Category;
import com.example.ProductManagement.Repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;



    public Page<Category> getAllCategory(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public List<Map> getCategoryById(Long categoryId) {

        List<Map> resList = new ArrayList<>();
        Map resMap = new LinkedHashMap();
        try {
            Optional<Category> category = categoryRepository.findById(categoryId);
            if (category.isPresent()) {
                resMap.put("message", "Category found Successfully......");
                resMap.put("result", category.get());
                resList.add(resMap);
            } else {

                resMap.put("message", "Category not found ......");
                resMap.put("result", null);
                resList.add(resMap);
            }

        } catch (Exception e) {
            resMap.put("message", "FAiled to fetch category......");
            resMap.put("result", null);
            resMap.put("Exception :","Exception Occured");
            resList.add(resMap);
        }
        return resList;
    }


    public List<Map> createCategory(Category category) {
        List<Map> resList = new ArrayList<>();
        Map resMap = new LinkedHashMap();
        try {
            if (category != null) {
                Category categoryObj = categoryRepository.save(category);
                resMap.put("message", "Category added Successfully......");
                resMap.put("result", categoryObj);
                resList.add(resMap);
            } else {
                resMap.put("message", "Failed to add category......");
                resMap.put("result", null);
                resList.add(resMap);
            }
        } catch (Exception e) {
            resMap.put("message", "Failed to Add category......");
            resMap.put("result", null);
            resMap.put("Exception :","Exception Occured");
            resList.add(resMap);
        }
        return resList;
    }


    public List<Map> updateCategory(Category category,Long categoryId) {

        List<Map> resList = new ArrayList<>();
        Map resMap = new LinkedHashMap();
        try {

            Optional<Category> categoryFind = categoryRepository.findById(categoryId);
            if (categoryFind.isPresent()) {
                Category c=categoryFind.get();
                c.setCategoryId(categoryId);
                if(category.getCategoryName()!=null && category.getCategoryName()!="")
                c.setCategoryName(category.getCategoryName());
                if(category.getCategoryProductId()!=null)
                    c.setCategoryProductId(category.getCategoryProductId());
                Category categoryObj = categoryRepository.save(c);
                resMap.put("message", "Category Updated Successfully......");
                resMap.put("result", categoryObj);
                resList.add(resMap);
            } else {
                resMap.put("message", "Failed to Update category ,id not found......");
                resMap.put("result", null);
                resList.add(resMap);
            }
        } catch (Exception e) {
            resMap.put("message", "Failed to update category......");
            resMap.put("result", null);
            resMap.put("Exception :","Exception Occured");
            resList.add(resMap);
        }
        return resList;
    }


    public List<Map> deleteCategoryById(Long categoryId) {
        List<Map> resList = new ArrayList<>();
        Map resMap = new LinkedHashMap();
        try {

            Optional<Category> category = categoryRepository.findById(categoryId);
            if (category.isPresent()) {
                Category cat = category.get();
                categoryRepository.delete(cat);
                resMap.put("message", "Category deleted Successfully......");
                resMap.put("result", cat);
                resList.add(resMap);

            } else {
                resMap.put("message", "Failed to delete category......");
                resMap.put("result", null);
                resList.add(resMap);
            }
        } catch (Exception e) {
            resMap.put("message", "Failed to update category......");
            resMap.put("result", null);
            resMap.put("Exception :","Exception Occured");
            resList.add(resMap);
        }
        return resList;
    }

}
