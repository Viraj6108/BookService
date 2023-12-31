package com.Book.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Book.Entity.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long>{

	 User findAdminByEmail(String email);
}
