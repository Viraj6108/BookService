package com.Book.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Book.Entity.Category;



@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{


    Category findByCategoryName(String categoryName);

}
