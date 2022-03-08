package com.parking.pls.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
@Table(name = "login")
public class Login {
	
	   @Id
	   @GeneratedValue(strategy=GenerationType.AUTO)
	   private Integer loginId;
	   @NotNull(message = "Username is required")
	   @Column(name="username", nullable = false)
	   private String username;
	   @NotNull(message = "Password is required")
//	   @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
	   @Column(name="password", nullable = false)
	   private String password;
//	   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	   @Column(name="created_at", nullable = true)
	   private LocalDateTime createdAt;
	   @ManyToOne(cascade=CascadeType.ALL)
	   @JoinColumn(name="roles_id")
	   private Role role;
	
	   public Login() {
		
	   }

	public Login(String username, String password, LocalDateTime createdAt, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.createdAt = createdAt;
		this.role = role;
	}


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

	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(loginId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		return Objects.equals(loginId, other.loginId);
	}

	   

}
