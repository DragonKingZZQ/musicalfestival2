package com.music.entity;

import java.io.Serializable;
import java.util.Date;


public class User implements Serializable {
	private static final long serialVersionUID = 6569487135753301028L;
	private Integer uid;
	private String username;
	private String password;
	private String token;
	
	
	public User() {
		super();
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
