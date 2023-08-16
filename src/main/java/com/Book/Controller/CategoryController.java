package com.Book.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Book.Entity.Category;
import com.Book.Exception.CategoryException;
import com.Book.Service.CategoryService;

@RestController
public class CategoryController {

	 @Autowired
	    private CategoryService categoryService;

	    // get Category by name
	    @GetMapping("/category")
	    public ResponseEntity<Category> getByCategoryName(@RequestParam String categoryName) throws CategoryException {
	        Category category = categoryService.getCategoryByName(categoryName);
	        if(category == null)
	        {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	        return ResponseEntity.status(HttpStatus.ACCEPTED).body(category);
	    }


	    // get all category by name
	    @GetMapping("/categories")
	    public ResponseEntity<List<Category>>getAllCategory() throws CategoryException{
//	        try {
	            List<Category> categoryList = categoryService.getAllCategoryByName();

	            if (categoryList.isEmpty()) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	            }

	            return ResponseEntity.ok(categoryList);
//	        } catch (CategoryException e) {
//	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//	        }
	    }

	    @GetMapping("/categoryid/{id}")
	    public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable("id") long categoryId) throws CategoryException {
	        Optional<Category> id = categoryService.getCategoryById(categoryId);
	        if(id.isEmpty())
	        {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	        return ResponseEntity.status(HttpStatus.FOUND).body(id);
	    }
}
