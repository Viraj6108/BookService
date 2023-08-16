package com.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Book.Entity.Category;
import com.Book.Exception.CategoryException;
import com.Book.Repository.CategoryRepository;
import com.Book.Service.CategoryServiceImpl;

public class CategoryServiceTest {

	
	@Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCategory_NewCategory_Success() throws CategoryException {
        // Mocking necessary methods
        when(categoryRepository.findByCategoryName("NewCategory")).thenReturn(null);
        when(categoryRepository.save(any(Category.class))).thenReturn(new Category(1L, "NewCategory"));

        // Call the method
        Category resultCategory = categoryService.addCategory("NewCategory");

        // Assertions
        assertNotNull(resultCategory);
        assertEquals("NewCategory", resultCategory.getCategoryName());
    }

    @Test
    void testAddCategory_ExistingCategory_Success() throws CategoryException {
        // Create an existing category
        Category existingCategory = new Category();
        existingCategory.setCategoryName("ExistingCategory");

        // Mocking necessary methods
        when(categoryRepository.findByCategoryName("ExistingCategory")).thenReturn(existingCategory);

        // Call the method
        Category resultCategory = categoryService.addCategory("ExistingCategory");

        // Assertions
        assertNotNull(resultCategory);
        assertEquals(existingCategory, resultCategory);
    }
    

    @Test
    void testGetCategoryByName_CategoryFound_Success() throws CategoryException {
        // Create a category
        Category existingCategory = new Category();
        existingCategory.setCategoryName("ExistingCategory");

        // Mocking necessary methods
        when(categoryRepository.findByCategoryName("ExistingCategory")).thenReturn(existingCategory);

        // Call the method
        Category resultCategory = categoryService.getCategoryByName("ExistingCategory");

        // Assertions
        assertNotNull(resultCategory);
        assertEquals(existingCategory, resultCategory);
    }

    @Test
    void testGetCategoryByName_CategoryNotFound() {
        // Mocking necessary methods
        when(categoryRepository.findByCategoryName("NonExistentCategory")).thenReturn(null);

        // Assertions
        assertThrows(CategoryException.class, () -> categoryService.getCategoryByName("NonExistentCategory"));
    }

    @Test
    void testGetAllCategoryByName_CategoriesFound_Success() throws CategoryException {
        // Create a list of categories
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1L, "Category1"));
        categoryList.add(new Category(2L, "Category2"));

        // Mocking necessary methods
        when(categoryRepository.findAll()).thenReturn(categoryList);

        // Call the method
        List<Category> resultCategoryList = categoryService.getAllCategoryByName();

        // Assertions
        assertNotNull(resultCategoryList);
        assertEquals(2, resultCategoryList.size());
    }

//    @Test
//    void testGetAllCategoryByName_NoCategoriesFound() {
//        // Mocking necessary methods
//        when(categoryRepository.findAll()).thenReturn(new ArrayList<>());
//
//        // Assertions
//        assertThrows(CategoryException.class, () -> categoryService.getAllCategoryByName());
//    }
}
