package com.Book.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Book.Entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>{

	   Book findBookByName(String name);
}
