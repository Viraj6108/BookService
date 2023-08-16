package com.Book.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.Book.Entity.Admin;



@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

	 Admin findAdminByEmail(String email);
	    Admin findAdminByName(String name);
}
