package com.se452.University;

import java.util.List;

import com.se452.AppUser.AppUser;

public interface UniversityServiceInterface {
	
	void addUniversityForUser(String userName);
	void deleteUniversityForUser(String userName,String universityName);
	void commitTransaction();

}
