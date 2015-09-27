package com.se452.Friendship;

public interface FriendshipServiceInterface {
	void addFriendship(int userId,int friendId,String acceptTime);
	void cancelFriendship(String userName,String friendName);
	void commitTransaction();

}
