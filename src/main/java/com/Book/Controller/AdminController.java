package com.Book.Controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Book.Entity.Admin;
import com.Book.Entity.logInDto;
import com.Book.Exception.AdminException;
import com.Book.Exception.CategoryException;
import com.Book.Service.AdminService;
import com.Book.Service.CategoryService;
import com.Book.Service.CustomerService;





@RestController
public class AdminController {

	
	 @Autowired
	    private AdminService adminService;
	    @Autowired
	    private CategoryService categoryService;

	    @Autowired
	    private CustomerService customerService;

	    @Autowired
	    private com.Book.Service.BookService bookService;
	    // Controller to register admin
	    @PostMapping("/admin/")
	    public ResponseEntity<Admin> registerAdmin(@Valid @RequestBody Admin admin) throws AdminException
	    {
	        
	            Admin registeredAdmin = adminService.registerAdmin(admin);
	            return ResponseEntity.status(HttpStatus.CREATED).body(registeredAdmin);
	        
	    }


	//   @DeleteMapping("/deletebook/{bookname}")
	//   public void deleteBook(@PathVariable("bookname") String bookname) throws BookException {
//	       bookService.deleteBook(bookname);
	//   }

	    // log in Admin

	  
	    
	    @PostMapping("/adminlogin/")
	    public ResponseEntity<String> login(@Valid @RequestBody logInDto logInDto) throws AdminException {
	        if (logInDto.getEmail() != null)
	        {
	            return ResponseEntity.status(HttpStatus.CREATED).body(adminService.adminLogin(logInDto));
	        }
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }


	    // Change password
	    @PutMapping("/adminpassword")

	    public ResponseEntity<Admin> changePassword(@Valid @RequestParam String email,
	                                                 @Valid  @RequestParam String oldPassword,
	                                                 @Valid  @RequestParam String newPassword) throws AdminException {

	       
	            Admin updatedCustomer = adminService.changePassword(email, oldPassword, newPassword);
	            return ResponseEntity.ok(updatedCustomer);
	        
	    }


	    // to add category

	    @PostMapping("/category")
	    public ResponseEntity<Object> addCategory( @RequestParam String categoryName) throws CategoryException {
	        if (categoryName == null)
	        {
	            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	        }
	        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.addCategory(categoryName));
	    }





	    // find admin by name

	    @GetMapping("/admin")
	    public ResponseEntity<Admin>  findAdminByName( @RequestParam String name) throws AdminException {
	        Admin admin = adminService.getAdminByName(name);
	        if(admin == null)
	        {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }

	        return ResponseEntity.status(HttpStatus.FOUND).body(admin);
	    }

	    // get list of admin

	    @GetMapping("admins")
	    public ResponseEntity<List<Admin>> getAllAdmins() throws AdminException {
	        List<Admin> adminList = adminService.getAllAdmins();

	        if(adminList.isEmpty())
	        {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	        }
	        return ResponseEntity.status(HttpStatus.FOUND).body(adminList);
	    }


	    // delete admin by name
	    @DeleteMapping("/admin/")
	    public ResponseEntity<String> deleteAdminByName(@RequestParam String name) throws AdminException {
	       
	            adminService.deleteAdminByName(name);
	            return ResponseEntity.ok("Admin deleted successfully");
	        
	    }
}
