package com.se452.AppUser;



public class Maintest {
	
	 public static void main(String[] args) throws Exception {
		  UserService us=new UserService();
		  
	//us.addUser("Tutue","xiyu332012@gmail.com", "F", "password");
	/*boolean ifValid=UserService.verifyExistingUser("Tutue", "password");
	if(ifValid==true) System.out.print("Valid");
	else  System.out.print("Not Valid");*/
		 
		 //UserService.deleteUser("Emma");
		us.uppdateExistingUserEmail("Tutu", "password", "password1");
		boolean  ifValid=us.verifyExistingUser("Tutu", "password1");
			if(ifValid==true) System.out.print("Valid");
			
			//UserService.uppdateExistingUserEmail("Emma", "email", "xiyu@eclipseLink.com");
			//UserService.uppdateExistingUserEmail("Emma", "gender", "M");
			
			us.commitTransaction();
}
	 
}
