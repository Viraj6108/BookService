package com.Book.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Book.Entity.Category;
import com.Book.Exception.CategoryException;
import com.Book.Repository.CategoryRepository;



@Service
public class CategoryServiceImpl implements CategoryService{

	 @Autowired
	    private CategoryRepository categoryRepository;

	    @Override
	    public Category addCategory(String categoryName) throws CategoryException {
	       Category existingCategory = categoryRepository.findByCategoryName(categoryName);

	        if (existingCategory != null) {
	            // If the category already exists, return it
	            return existingCategory;
	        } else {
	            // If the category does not exist, create a new category
	            Category newCategory = new Category();
	            newCategory.setCategoryName(categoryName);
	           newCategory=  categoryRepository.save(newCategory);
	            // Save the new category to the database
	            return newCategory;
	        }
	    }

	    @Override
	    public Category getCategoryByName(String categoryName) throws CategoryException {
	        Category categoryFromDB = categoryRepository.findByCategoryName(categoryName);
	        if(categoryFromDB == null)
	        {
	            throw new CategoryException("Category not found");
	        }
	        return categoryRepository.findByCategoryName(categoryName);
	    }

	    @Override
	    public List<Category> getAllCategoryByName() throws CategoryException {

	        List<Category> categoryListFromDB = (List<Category>) categoryRepository.findAll();
	        if(categoryListFromDB == null)
	        {
	            throw new CategoryException("No categories found");

	        }
	        return categoryListFromDB;


	    }

	    public Optional<Category> getCategoryById(long categoryId) throws CategoryException
	    {
	        Optional<Category> category =categoryRepository.findById(categoryId);

	        if(category.isEmpty())
	        {
	            throw new CategoryException("Category with " + categoryId +"not found");
	        }
	        return category;
	    }

		
}
