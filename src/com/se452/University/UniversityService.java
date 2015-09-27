package com.se452.University;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.se452.AppUser.AppUser;

public class UniversityService implements UniversityServiceInterface {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager ;
	
	public UniversityService (){
			entityManagerFactory = Persistence.createEntityManagerFactory("SE452EclipseLink2");
			entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();
}
	@Override
	public void addUniversityForUser(String userName) {
		

	}

	@Override
	public void deleteUniversityForUser(String userName, String universityName) {
	
	}

	
	@Override
	public void commitTransaction() {
		
		entityManager.getTransaction().commit();
	    entityManager.close();
	    entityManagerFactory.close();
	}

}
