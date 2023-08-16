package com.Book.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Book.Entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

//	Orders findById(int id);
}
