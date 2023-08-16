package com.Book;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Book.Entity.Book;
import com.Book.Entity.Category;
import com.Book.Exception.BookException;
import com.Book.Exception.CategoryException;
import com.Book.Repository.BookRepository;
import com.Book.Repository.CategoryRepository;
import com.Book.Service.BookServiceImpl;
import com.Book.Service.CategoryService;

public class BookServiceTest {

	
	 @Mock
	    private BookRepository bookRepository;

	    @Mock
	    private CategoryService categoryService;

	    @Mock
	    private CategoryRepository categoryRepository;

	    @InjectMocks
	    private BookServiceImpl bookService;
	    

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.initMocks(this);
	    }
	    
	    @Test
	    public void testAddBooksToCategory_Success() throws BookException, CategoryException {
	        Book bookToAdd = new Book();
	        bookToAdd.setName("BookName");

	        Category existingCategory = new Category();
	        existingCategory.setCategoryName("TestCategory");

	        when(bookRepository.findBookByName(eq("BookName"))).thenReturn(null);
	        when(categoryService.getCategoryByName(eq("TestCategory"))).thenReturn(existingCategory);
	        when(bookRepository.save(any(Book.class))).thenReturn(bookToAdd);

	        Book result = bookService.addBooksToCategory(bookToAdd, "TestCategory");

	        verify(bookRepository, times(1)).findBookByName(eq("BookName"));
	        verify(categoryService, times(1)).getCategoryByName(eq("TestCategory"));
	        verify(bookRepository, times(1)).save(any(Book.class));
	        verify(categoryRepository, times(1)).save(eq(existingCategory));
	       
	    }

	    @Test
	    public void testAddBooksToCategory_ExistingBook() throws CategoryException {
	        Book bookToAdd = new Book();
	        bookToAdd.setName("ExistingBook");

	        when(bookRepository.findBookByName(eq("ExistingBook"))).thenReturn(new Book());

	        try {
	            bookService.addBooksToCategory(bookToAdd, "TestCategory");
	        } catch (BookException e) {
	            // Assert the error message or other properties of the exception
	            return;
	        }

	        // Fail the test if an exception is not thrown
	        fail("Expected BookException due to existing book");
	    }

	    @Test
	    public void testAddBooksToCategory_CategoryNotFound() throws BookException, CategoryException {
	        Book bookToAdd = new Book();
	        bookToAdd.setName("BookName");

	        when(bookRepository.findBookByName(eq("BookName"))).thenReturn(null);
	        when(categoryService.getCategoryByName(eq("TestCategory"))).thenReturn(null);

	        try {
	            bookService.addBooksToCategory(bookToAdd, "TestCategory");
	        } catch (CategoryException e) {
	            // Assert the error message or other properties of the exception
	            return;
	        }

	        // Fail the test if an exception is not thrown
	        fail("Expected CategoryException due to missing category");
	    }
	    
	    @Test
	    public void testGetBookByName_Success() throws BookException {
	        String bookNameToFind = "BookName";
	        Book bookFromDB = new Book();
	        bookFromDB.setName(bookNameToFind);

	        when(bookRepository.findBookByName(eq(bookNameToFind))).thenReturn(bookFromDB);

	        Book result = bookService.getBookByName(bookNameToFind);

	        verify(bookRepository, times(1)).findBookByName(eq(bookNameToFind));
	        // Assert the result matches the expected bookFromDB
	    }

	    @Test
	    public void testGetBookByName_BookNotFound() {
	        String bookNameToFind = "NonExistentBook";

	        when(bookRepository.findBookByName(eq(bookNameToFind))).thenReturn(null);

	        try {
	            bookService.getBookByName(bookNameToFind);
	        } catch (BookException e) {
	            // Assert the error message or other properties of the exception
	            return;
	        }

	        // Fail the test if an exception is not thrown
	        fail("Expected BookException due to book not found");
	    }
}
