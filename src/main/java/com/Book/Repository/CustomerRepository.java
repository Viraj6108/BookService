package com.Book.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Book.Entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Customer findUserByUsernameAndEmail(String name , String email);

	public Customer findUserByemail(String email);

	public Customer findByUsername(String username);
}
