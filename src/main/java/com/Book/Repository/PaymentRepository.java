package com.Book.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Book.Entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
