package com.se452.FriendRequest;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.se452.AppUser.AppUser;
import com.se452.FriendRequest.FriendRequest;
import com.se452.Friendship.Friendship;
import com.se452.Friendship.FriendshipService;
import com.se452.Status.Status;

public class FriendRequestService implements FriendRequestServiceInterface{
	
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager ;
	
	public FriendRequestService (){
			entityManagerFactory = Persistence.createEntityManagerFactory("SE452EclipseLink2");
			entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();
}
 
	/*Pass test*/
	@Override
	public void sendFriendRequest(String userName, String friendName)
	{
		List result = entityManager.createQuery("select au from AppUser au where au.app_user_name=:userName")
		          .setParameter("userName", userName).getResultList();
		AppUser user=(AppUser) result.get(0);
		List result2 = entityManager.createQuery("select au from AppUser au where au.app_user_name=:userName")
		          .setParameter("userName", friendName).getResultList();
		AppUser friend=(AppUser) result2.get(0);
		
		FriendRequest fe=new FriendRequest();
		fe.setAu(user);
		fe.setFriend(friend);
		long time = System.currentTimeMillis();
		Timestamp ts=new Timestamp(time);
		String S = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(ts);
		fe.setRequestSendTime(S);
		fe.setRequestStatus(Status.PENDING);
		fe.setRequestUpdateTime(S);
		entityManager.persist(fe);
		entityManager.flush();
	}
	
	/*Pass test*/
	@Override
	public void changeFriendReqestStatus(String userName, String friendName,String timeSent,Status s)
	{
		
		List result = entityManager.createQuery("select au from AppUser au where au.app_user_name=:userName")
		          .setParameter("userName", userName).getResultList();
		AppUser user=(AppUser) result.get(0);
		int userId=user.getApp_user_id();
		List result2 = entityManager.createQuery("select au from AppUser au where au.app_user_name=:userName")
		          .setParameter("userName", friendName).getResultList();
		AppUser friend=(AppUser) result2.get(0);
		int friendId=friend.getApp_user_id();
		Query qy=entityManager.createQuery("select fr from FriendRequest fr where fr.au.app_user_id=:userID and"
				+ " fr.friend.app_user_id=:friendID and fr.requestSendTime=:t");
		qy.setParameter("userID", userId);
		qy.setParameter("friendID", friendId);
		qy.setParameter("t", timeSent);
		List list=qy.getResultList();
		FriendRequest fr=(FriendRequest) list.get(0);
		Status oldStatus=fr.getRequestStatus();
		if(!oldStatus.equals(s)){
			long time = System.currentTimeMillis();
			Timestamp ts=new Timestamp(time);
			String S = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(ts);
			fr.setRequestStatus(s);
			fr.setRequestUpdateTime(S);
			if(S.equals("ACCEPT")){
				FriendshipService fs=new FriendshipService();
				fs.addFriendship(userId, friendId, S);
				fs.addFriendship(friendId, userId, S);
				fs.commitTransaction();
		}
		entityManager.persist(fr);
		entityManager.flush();
		}
		
	}



	/*Pass Test*/
	@Override
	public  List<FriendRequest> viewFriendRequest(String userName) {
		List result = entityManager.createQuery("select au from AppUser au where au.app_user_name=:userName")
		          .setParameter("userName", userName).getResultList();
		AppUser user=(AppUser) result.get(0);
		int userId=user.getApp_user_id();
		List list = entityManager.createQuery("select fr from FriendRequest fr where fr.au.app_user_id=:userID")
		          .setParameter("userID", userId).getResultList();
		return list;
	}

	@Override
	public void cancelFriendRequest(String userName, String friendName,
			String timeSent) {

		List result = entityManager.createQuery("select au from AppUser au where au.app_user_name=:userName")
		          .setParameter("userName", userName).getResultList();
		AppUser user=(AppUser) result.get(0);
		int userId=user.getApp_user_id();
		List result2 = entityManager.createQuery("select au from AppUser au where au.app_user_name=:userName")
		          .setParameter("userName", friendName).getResultList();
		AppUser friend=(AppUser) result2.get(0);
		int friendId=friend.getApp_user_id();
		Query qy=entityManager.createQuery("select fr from FriendRequest fr where fr.au.app_user_id=:userID and"
				+ " fr.friend.app_user_id=:friendID and fr.requestSendTime=:t");
		qy.setParameter("userID", userId);
		qy.setParameter("friendID", friendId);
		qy.setParameter("t", timeSent);
		List list=qy.getResultList();
		FriendRequest fr=(FriendRequest) list.get(0);
		entityManager.remove(fr);
		entityManager.flush();
		
	}

	

	@Override
	public void commitTransaction() {
		
		entityManager.getTransaction().commit();
	    entityManager.close();
	    entityManagerFactory.close();
	}

}
