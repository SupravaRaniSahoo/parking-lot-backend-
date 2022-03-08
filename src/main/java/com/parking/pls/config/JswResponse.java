package com.parking.pls.config;

public class JswResponse {

	String token;
	
	public JswResponse() {
		
	}

	public JswResponse(String token) {
		super();
		this.token = token;
	}



	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}
