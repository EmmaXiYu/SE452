package com.se452.Friendship;

public class MainFriendshipTest {

	public static void main(String[] args) {
		FriendshipService fss=new FriendshipService();
		fss.cancelFriendship("Emma", "Tutu");
		fss.commitTransaction();
		

	}

}
