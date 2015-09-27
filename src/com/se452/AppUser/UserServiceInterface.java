package com.se452.AppUser;

import java.security.NoSuchAlgorithmException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManager;

public interface UserServiceInterface {

	void addUser(String userName, String userEmail, String userGender, String userPassword,int age) throws NoSuchAlgorithmException;
	boolean verifyExistingUser(String userName, String userPassword) throws NoSuchAlgorithmException;
	AppUser deleteUser(String userName);
	void uppdateExistingUserEmail(String userName,String para, String value) throws NoSuchAlgorithmException;
	void commitTransaction();
	//search functionality
	
}
