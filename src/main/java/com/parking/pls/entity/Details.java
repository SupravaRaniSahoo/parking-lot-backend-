package com.parking.pls.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="details")
public class Details {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer detailsId;
	@Column(name="user_name")
	private String userName;
	@Column(name="password")
	private String password;
	
	public Details() {
		
	}

	public Details(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	
	public Integer getDetailsId() {
		return detailsId;
	}
	public void setDetailsId(Integer detailsId) {
		this.detailsId = detailsId;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(detailsId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Details other = (Details) obj;
		return Objects.equals(detailsId, other.detailsId);
	}

}
