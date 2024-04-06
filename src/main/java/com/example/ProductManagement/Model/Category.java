package com.example.ProductManagement.Model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="category")
@Data

public class Category {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "categoryName",columnDefinition = "varchar(50) default null")
    private String categoryName;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> categoryProductId;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Product> getCategoryProductId() {
		return categoryProductId;
	}

	public void setCategoryProductId(List<Product> categoryProductId) {
		this.categoryProductId = categoryProductId;
	}
    
    
}