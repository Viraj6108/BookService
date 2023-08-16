package com.Book.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Book.Entity.Book;
import com.Book.Entity.Category;
import com.Book.Exception.BookException;
import com.Book.Exception.CategoryException;
import com.Book.Repository.BookRepository;
import com.Book.Repository.CartRepository;
import com.Book.Repository.CategoryRepository;

@Service
public class BookServiceImpl implements BookService{

	 @Autowired
	    private BookRepository bookRepository;
	    @Autowired
	    private CategoryService categoryService;
	    @Autowired
	    private CategoryRepository categoryRepository;

	    @Autowired
	    private CartRepository cartRepository;



	    // add book to category and database
	@Override
	public Book addBooksToCategory(Book book, String categoryName) throws BookException, CategoryException {
	    // Check if a book with the same name already exists
	    Optional<Book> existingBook = Optional.ofNullable(bookRepository.findBookByName(book.getName()));
	    if (existingBook.isPresent()) {
	        throw new BookException("Book is already registered");
	    }

	    // Retrieve the category by name
	    Category existingCategory = categoryService.getCategoryByName(categoryName);
	    if (existingCategory == null) {
	        throw new CategoryException("Category not found");
	    }


	    book.setCategory(existingCategory);
	    existingCategory.getBookList().add(book);

	    // Save the book and update the category
	    Book newBook = bookRepository.save(book);
	    categoryRepository.save(existingCategory);

	    return newBook;
	}





	    @Override
	    public Book getBookByName(String bookName) throws BookException {
	    Book book = bookRepository.findBookByName(bookName);
	    if (book == null)
	    {
	        throw new BookException(bookName+" "+"book not found");
	    }
	    return  book;

	    }


	    @Override
	    public void deleteBook(String name) throws BookException {
	        Book book = bookRepository.findBookByName(name);
	        if (book == null) {
	            throw new BookException("Book not found with the given name");
	        }
	        bookRepository.delete(book);
	    }






	    @Override
	    public Book editBookDetails(Book book) throws BookException {

	        return null;
	    }

	    @Override
	    public Book getBookById(long bookId) throws BookException {
	        Optional<Book> book = bookRepository.findById(bookId);
	        if(book== null)
	        {
	            throw new BookException("Book not found by id"+ bookId);
	        }

	        return book.get();
	    }

	    @Override
	    public List<Book> getAllBooks() throws BookException {
	        List<Book> bookList = bookRepository.findAll();
	        if(bookList.isEmpty())
	        {
	            throw new BookException("Book list is empty");
	        }
	        return bookList;
	    }


}
