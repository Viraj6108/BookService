package com.Book.Service;

import java.util.List; 



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Book.Entity.Admin;
import com.Book.Entity.logInDto;
import com.Book.Exception.AdminException;
import com.Book.Repository.AdminRepository;
import com.Book.Repository.BookRepository;





@Service
public class AdminServiceImpl implements AdminService{

	
	  @Autowired
	    private AdminRepository adminRepository;

	    @Autowired
	    private BookRepository bookRepository;

	    @Autowired
	    private CategoryService categoryService;

	    @Override
	    public Admin registerAdmin(Admin admin) throws AdminException {
	        Admin adminFromDB = adminRepository.findAdminByEmail(admin.getEmail());

	        if(adminFromDB != null && adminFromDB.getEmail().equals(admin.getEmail()))
	        {

	            throw new AdminException("Admin is already registered with this email");
	        }
	        admin.setRole("Admin");

	        try{


	            return   adminRepository.save(admin);
	        }catch (Exception e)
	        {
	           throw new AdminException("Failed to register please try again");
	        }

	    }

	    // Service for adding books


	    @Override
	    public String adminLogin(logInDto logInDto) throws AdminException {
	        Admin fromDB = adminRepository.findAdminByEmail(logInDto.getEmail());
	        if (fromDB != null) {
	            // if email found in DB then check for password
	            if (fromDB.getEmail().equals(logInDto.getEmail()) && fromDB.getPassword().equals(logInDto.getPassword())) {
	                return "Admin logged in successfully";
	            }else {
	                throw new AdminException("Incorrect email or password ");
	            }

	        }
	      throw new AdminException("Failed to login try again");
	    }


	    /// service for  deleting books





	    // Change password
	    @Override
	    public Admin changePassword(String email, String oldPassword, String newPassword) throws AdminException {
	        // check if the user is present or not
	        Admin adminFromDB = adminRepository.findAdminByEmail(email);


	        if(adminFromDB!= null)
	        {
	            if(adminFromDB.getPassword().equals(oldPassword))
	            {
	                adminFromDB.setPassword(newPassword);
	                return adminRepository.save(adminFromDB);
	            }
	            else{
	                throw new AdminException("Password does not match");
	            }
	        }else {
	            throw new AdminException("Customer is not registered");
	        }
	    }


	    // get Admin by name
	    public Admin getAdminByName(String name) throws AdminException
	    {
	        Admin adminName = adminRepository.findAdminByName(name);
	        if(adminName == null)
	        {
	            throw new AdminException("Admin not found by this name");
	        }
	        return adminName;
	    }

	    // get all admins
	    public List<Admin> getAllAdmins()
	    {
	        return adminRepository.findAll();
	    }

	    // Delete admin by name
	    public void deleteAdminByName(String name) throws AdminException {
	        Admin adminToDelete = adminRepository.findAdminByName(name);

	        // If adminToDelete is null, it means no admin with the given name was found
	        if (adminToDelete == null) {
	            throw new AdminException("Admin not found with the given name: " + name);
	        }

	        // If admin is found, delete it from the database
	        adminRepository.delete(adminToDelete);
	    }

	    

   
}
