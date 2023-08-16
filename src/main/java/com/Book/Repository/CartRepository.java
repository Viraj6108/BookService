package com.Book.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Book.Entity.Book;
import com.Book.Entity.Cart;



@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

	 @Query("SELECT c FROM Cart c WHERE :book member of c.bookList")
	    List<Cart> findCartsByBook(@Param("book") Book book);
}
