package com.parking.pls.dto;

public class DetailsDTO {
	
  private Integer detailsId;
  private String userName;
  private String password;
  
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
public String toString() {
	return "DetailsDTO [detailsId=" + detailsId + ", userName=" + userName + ", password=" + password + "]";
} 
 
}
