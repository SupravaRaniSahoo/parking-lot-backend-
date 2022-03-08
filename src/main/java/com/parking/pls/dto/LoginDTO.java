package com.parking.pls.dto;

import java.time.LocalDateTime;

public class LoginDTO {
	
	private Integer loginId;
	private String username;
	private String password;
	private LocalDateTime createdAt;
	private RoleDTO roleDTO;
	
	
	public Integer getLoginId() {
		return loginId;
	}
	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public RoleDTO getRoleDTO() {
		return roleDTO;
	}
	public void setRoleDTO(RoleDTO roleDTO) {
		this.roleDTO = roleDTO;
	}
	
	
	@Override
	public String toString() {
		return "LoginDTO [loginId=" + loginId + ", username=" + username + ", password=" + password + ", createdAt="
				+ createdAt + ", roleDTO=" + roleDTO + "]";
	}
	
	

}
