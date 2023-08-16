package com.Book;
import static org.junit.jupiter.api.Assertions.fail; 
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Book.Entity.Admin;
import com.Book.Entity.logInDto;
import com.Book.Exception.AdminException;
import com.Book.Repository.AdminRepository;
import com.Book.Service.AdminServiceImpl;

public class AdminServiceTest {

	
	  @Mock
	    private AdminRepository adminRepository;

	    @InjectMocks
	    private AdminServiceImpl adminService;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.initMocks(this);
	    }

	    @Test
	    public void testRegisterAdmin_Success() throws AdminException {
	        Admin adminToRegister = new Admin();
	        adminToRegister.setEmail("veer6676@gmail.com");
	        adminToRegister.setRole("Admin");

	        when(adminRepository.findAdminByEmail(anyString())).thenReturn(null);
	        when(adminRepository.save(any(Admin.class))).thenReturn(adminToRegister);

	        Admin result = adminService.registerAdmin(adminToRegister);

	        verify(adminRepository, times(1)).findAdminByEmail(anyString());
	        verify(adminRepository, times(1)).save(any(Admin.class));
	       
	    }

	    @Test
	    public void testRegisterAdmin_DuplicateEmail() throws AdminException {
	        Admin adminToRegister = new Admin();
	        adminToRegister.setEmail("veer6676@gmail.com");

	        when(adminRepository.findAdminByEmail(anyString())).thenReturn(adminToRegister);

	        try {
	            adminService.registerAdmin(adminToRegister);
	        } catch (AdminException e) {
	            
	            return;
	        }

	        // Fail the test if an exception is not thrown
	        fail("Expected AdminException due to duplicate email");
	    }

	    @Test
	    public void testRegisterAdmin_FailedToRegister() {
	        Admin adminToRegister = new Admin();
	        adminToRegister.setEmail("test@example.com");

	        when(adminRepository.findAdminByEmail(anyString())).thenReturn(null);
	        when(adminRepository.save(any(Admin.class))).thenThrow(new RuntimeException("Mocked error"));

	        try {
	            adminService.registerAdmin(adminToRegister);
	        } catch (AdminException e) {
	            // Assert the error message or other properties of the exception
	            return;
	        }

	        // Fail the test if an exception is not thrown
	        fail("Expected AdminException due to failed registration");
	    }
	    
	    
	    // for log in 
	    
	    @Test
	    public void testAdminLogin_Success() throws AdminException {
	        logInDto loginData = new logInDto("test@example.com", "password");
	        Admin adminFromDB = new Admin();
	        adminFromDB.setEmail("test@example.com");
	        adminFromDB.setPassword("password");

	        when(adminRepository.findAdminByEmail(anyString())).thenReturn(adminFromDB);

	        String result = adminService.adminLogin(loginData);

	        verify(adminRepository, times(1)).findAdminByEmail(anyString());
	        
	    }
	    
	    
	    @Test
	    public void testAdminLogin_IncorrectEmailOrPassword() {
	        logInDto loginData = new logInDto("test@example.com", "wrongpassword");
	        Admin adminFromDB = new Admin();
	        adminFromDB.setEmail("test@example.com");
	        adminFromDB.setPassword("password");

	        when(adminRepository.findAdminByEmail(anyString())).thenReturn(adminFromDB);

	        try {
	            adminService.adminLogin(loginData);
	        } catch (AdminException e) {
	           
	            return;
	        }

	        // Fail the test if an exception is not thrown
	        fail("Expected AdminException due to incorrect email or password");
	    }
	    
	    
	    @Test
	    public void testAdminLogin_UserNotFound() {
	        logInDto loginData = new logInDto("test@example.com", "password");

	        when(adminRepository.findAdminByEmail(anyString())).thenReturn(null);

	        try {
	            adminService.adminLogin(loginData);
	        } catch (AdminException e) {
	          
	            return;
	        }
	        fail("Expected AdminException due to user not found");
	    }
	    
	    @Test
	    public void testGetAdminByName_Success() throws AdminException {
	        String adminNameToRetrieve = "AdminName";
	        Admin adminFromDB = new Admin();
	        adminFromDB.setName(adminNameToRetrieve);

	        when(adminRepository.findAdminByName(eq(adminNameToRetrieve))).thenReturn(adminFromDB);

	        Admin result = adminService.getAdminByName(adminNameToRetrieve);

	        verify(adminRepository, times(1)).findAdminByName(eq(adminNameToRetrieve));
	        // Assert the returned admin matches the expected adminFromDB
	    }

	    @Test
	    public void testGetAdminByName_AdminNotFound() {
	        String adminNameToRetrieve = "NonExistentAdminName";

	        when(adminRepository.findAdminByName(eq(adminNameToRetrieve))).thenReturn(null);

	        try {
	            adminService.getAdminByName(adminNameToRetrieve);
	        } catch (AdminException e) {
	            // Assert the error message or other properties of the exception
	            return;
	        }

	        // Fail the test if an exception is not thrown
	        fail("Expected AdminException due to admin not found");
	    }

	    @Test
	    public void testGetAllAdmins_Success() {
	        List<Admin> adminList = new ArrayList();
	        adminList.add(new Admin());
	        adminList.add(new Admin());

	        when(adminRepository.findAll()).thenReturn(adminList);

	        List<Admin> result = adminService.getAllAdmins();

	        verify(adminRepository, times(1)).findAll();
	        
	    }

	    @Test
	    public void testGetAllAdmins_NoAdminsFound() {
	        List<Admin> adminList = new ArrayList<>();

	        when(adminRepository.findAll()).thenReturn(adminList);

	        List<Admin> result = adminService.getAllAdmins();

	        verify(adminRepository, times(1)).findAll();
	       
	    }
}
	








