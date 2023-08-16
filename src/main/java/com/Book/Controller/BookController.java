package com.Book.Controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Book.Entity.Book;
import com.Book.Exception.BookException;
import com.Book.Exception.CategoryException;
import com.Book.Service.BookService;



@RestController
public class BookController {

	
	 @Autowired
	    private BookService bookService;

	    // by name
	    @PostMapping("/tocategory")
	    public Book addBookToCategory(@RequestBody Book book, @RequestParam String categoryName) throws CategoryException, BookException {
	        return bookService.addBooksToCategory(book,categoryName);
	    }


	    @GetMapping("/getAllBooks")
	    public List<Book> getAllBooks() throws BookException {
	        return bookService.getAllBooks();
	    }


	    @DeleteMapping("/deleteBook")
	    public ResponseEntity<Book> deleteBook (@RequestParam ("bookName") String bookName) throws BookException {
	        Book book = bookService.getBookByName(bookName);
	     if (book.getName().isEmpty())
	     {
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	     }
	        bookService.deleteBook(bookName);
	     return ResponseEntity.status(HttpStatus.OK).body(book);
	    }

	    @GetMapping("/getBook/{bookName}")
	    public Book getBookByName(@PathVariable String bookName) throws BookException {
	        return bookService.getBookByName(bookName);
//	        if (book != null)
//	        return ResponseEntity.status(HttpStatus.OK).body(book);
//	        else
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	    }
}
