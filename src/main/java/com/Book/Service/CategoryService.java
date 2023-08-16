package com.Book.Service;

import java.util.List;
import java.util.Optional;

import com.Book.Entity.Category;
import com.Book.Exception.CategoryException;


public interface CategoryService {

	 Category addCategory(String categoryName) throws CategoryException;

	    Category getCategoryByName(String categoryName) throws CategoryException;


	Optional<Category> getCategoryById(long categoryId) throws CategoryException;

	    List<Category> getAllCategoryByName() throws CategoryException;
}
