package com.Book.Service;

import java.util.List; 

import com.Book.Entity.Admin;
import com.Book.Entity.logInDto;
import com.Book.Exception.AdminException;



public interface AdminService {
	public Admin registerAdmin(Admin admin) throws AdminException;


	public String adminLogin(logInDto logInDto) throws AdminException;

	public Admin changePassword(String email,String oldPassword,String newPassword) throws AdminException;

	public Admin getAdminByName(String name) throws AdminException;

	public List<Admin> getAllAdmins() throws  AdminException;

	public void deleteAdminByName(String name) throws AdminException;
}
