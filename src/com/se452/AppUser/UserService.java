package com.se452.AppUser;

import java.security.NoSuchAlgorithmException;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class UserService implements UserServiceInterface{
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager ;
	
	public UserService (){
			entityManagerFactory = Persistence.createEntityManagerFactory("SE452EclipseLink2");
			entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();
}
 
	

	public void addUser(String userName, String userEmail, String userGender, String userPassword,int age) throws NoSuchAlgorithmException
	{
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SE452EclipseLink2");
	    EntityManager entityManager = entityManagerFactory.createEntityManager();
	    entityManager.getTransaction().begin();
	    AppUser au=new AppUser();
	    au.setApp_user_name(userName);
	    au.setEmail_Address(userEmail);
	    au.setGender(userGender);
	    au.setPassword(userPassword);
	    au.setAge(age);
	    entityManager.persist(au);
	    entityManager.flush();
	   
	}
	
	public boolean verifyExistingUser(String userName, String userPassword) throws NoSuchAlgorithmException

	{
		boolean ifValid=true;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SE452EclipseLink2");
	    EntityManager entityManager = entityManagerFactory.createEntityManager();
	    entityManager.getTransaction().begin();
	    List result=entityManager.createQuery("select au.password from AppUser au where au.app_user_name=:userName")
		          .setParameter("userName", userName).getResultList();
		String passwordDB=result.get(0).toString();
		PasswordEncryption pe=new PasswordEncryption ();
		pe.setOriginalPassword(userPassword);
		pe.passwordEncrypt();
		String passwordEncrypted=pe.getEncryptedPassword();
		if(passwordEncrypted.equals(passwordDB))
			{ifValid=true;
			}
		else {
			ifValid=false;
		}
		return ifValid;	
		
	}
	
	public AppUser deleteUser(String userName)
	{
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SE452EclipseLink2");
	    EntityManager entityManager = entityManagerFactory.createEntityManager();
	    entityManager.getTransaction().begin();
		List result = entityManager.createQuery("select au from AppUser au where au.app_user_name=:userName")
		          .setParameter("userName", userName).getResultList();
		AppUser testUser=(AppUser) result.get(0);
		entityManager.remove(testUser);
	    entityManager.flush();
	    return testUser;
	}
	public void uppdateExistingUserEmail(String userName,String para, String value) throws NoSuchAlgorithmException
	{
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SE452EclipseLink2");
	    EntityManager entityManager = entityManagerFactory.createEntityManager();
	    entityManager.getTransaction().begin();
	    List result = entityManager.createQuery("select au from AppUser au where au.app_user_name=:userName")
		          .setParameter("userName", userName).getResultList();
	    AppUser testUser=(AppUser) result.get(0);
	    if(para.toUpperCase().equals("PASSWORD"))
	    {
	    	testUser.setPassword(value);
	    }
	    else if(para.toUpperCase().equals("GENDER"))
	    {
	    	testUser.setGender(value);
	    } 
	    else if(para.toUpperCase().equals("EMAIL"))
	    {
	    	testUser.setEmail_Address(value);
	    }
	    entityManager.persist(testUser);
	    entityManager.flush();
	}
	
	@Override
	public void commitTransaction() {
		
		entityManager.getTransaction().commit();
	    entityManager.close();
	    entityManagerFactory.close();
	}
	
}
