package com.se452.AppUser;



import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.se452.Friendship.Friendship;
import com.se452.FriendRequest.FriendRequest;
import com.se452.University.University;






@Entity
@Table(name="APPUSER")
@SequenceGenerator(name="User_seq", initialValue=1,allocationSize=1)
public class AppUser{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="User_seq")
	@Column(name="APPUSERID",nullable=false)
	
	private int app_user_id;
	@Column(name="APPUSERNAME",nullable=false,unique=true,length=30)
	private String app_user_name;
	@Column(name="USEREMAILADDRESS",nullable=false,length=320)
	private String email_Address;
	@Column(name="age",nullable=false,length=320)
	private int age;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<Friendship> getFriendship() {
		return friendship;
	}
	public void setFriendship(List<Friendship> friendship) {
		this.friendship = friendship;
	}

	@Column(name="PASSWORD",nullable=false,length=40)
	private String password;
	@Column(name="GENDER",length=10)
	private String gender;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="au")
	private List<FriendRequest> friendrequest;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private List<Friendship> friendship;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="au")
	private List<University> university;
 

	public List<University> getUniversity() {
		return university;
	}
	public void setUniversity(List<University> university) {
		this.university = university;
	}
	public int getApp_user_id() {
		return app_user_id;
	}
	public List<FriendRequest> getFriendrequest() {
		return friendrequest;
	}
	public void setFriendrequest(List<FriendRequest> friendrequest) {
		this.friendrequest = friendrequest;
	}
	public void setApp_user_id(int app_user_id) {
		this.app_user_id = app_user_id;
	}
	
	public String getApp_user_name() {
		return app_user_name;
	}
	
	public void setApp_user_name(String app_user_name) {
		this.app_user_name = app_user_name;
	}
	
	public String getEmail_Address() {
		return email_Address;
	}
	public void setEmail_Address(String email_Address) {
		this.email_Address = email_Address;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) throws NoSuchAlgorithmException {
		
	PasswordEncryption pe=new PasswordEncryption ();
	pe.setOriginalPassword(password);
	pe.passwordEncrypt();
	this.password=pe.getEncryptedPassword();
		
	}

	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
	

}
