package com.Book.Service;

import java.util.List;

import com.Book.Entity.Book;
import com.Book.Exception.BookException;
import com.Book.Exception.CategoryException;



public interface BookService {

	 void deleteBook(String name) throws BookException;

	    public Book editBookDetails (Book book) throws BookException;

	    public Book getBookById(long bookId) throws BookException;

	    public List<Book> getAllBooks () throws BookException;
	  //  public Book addBooksToCategory(Book book, Long categoryId) throws BookException, CategoryException;

	    // add book to category and database
	    Book addBooksToCategory(Book book, String categoryName) throws BookException, CategoryException;

	    public Book getBookByName(String bookName) throws BookException;
}
