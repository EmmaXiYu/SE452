package com.se452.University;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.se452.AppUser.AppUser;



@Entity
@Table(name="UNIVERSITY")
@SequenceGenerator(name="University_seq", initialValue=1,allocationSize=1)
public class University {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="University_seq")
	private int UniversityId;
	@ManyToOne
    @JoinColumn(name="USER_ID", nullable=false)
	private AppUser au;
	@Column(name="University_Name",nullable=false,unique=true,length=30)
	private String UniversityName;
	
	
	public int getUniversityId() {
		return UniversityId;
	}
	public void setUniversityId(int universityId) {
		UniversityId = universityId;
	}
	public AppUser getAu() {
		return au;
	}
	public void setAu(AppUser au) {
		this.au = au;
	}
	public String getUniversityName() {
		return UniversityName;
	}
	public void setUniversityName(String universityName) {
		UniversityName = universityName;
	}
	
	
	
}
