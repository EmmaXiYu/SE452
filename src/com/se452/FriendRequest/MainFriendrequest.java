package com.se452.FriendRequest;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.se452.AppUser.AppUser;
import com.se452.Status.Status;

public class MainFriendrequest {

	public static void main(String[] args) {
		
		FriendRequestService frs=new FriendRequestService();
		//frs.sendFriendRequest("Tutu", "Emma");
	
		//int userId=24;
		//int friendId=17;
		//String time="09/25/2015 16:22:42";
		frs.changeFriendReqestStatus("Tutu","Emma","09/25/2015 16:22:42",Status.ACCEPT);
		
		/*List list=frs.viewFriendRequest("Emma");
		for(int i=0;i<list.size();i++)
		{
			FriendRequest fr=(FriendRequest) list.get(i);
			System.out.println(fr.getAu().getApp_user_name());
			System.out.println(fr.getFriend().getApp_user_name());
			System.out.println(fr.getRequestStatus());
			System.out.println(fr.getRequestSendTime());
		
		}*/
		//frs.cancelFriendRequest("Tutu", "Emma", "09/25/2015 14:34:01");
		frs.commitTransaction();

	}

}
