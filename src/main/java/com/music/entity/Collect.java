package com.music.entity;



public class Collect {
	private int perid;
	private String openid;
	private int send;
	public int getPerid() {
		return perid;
	}
	public void setPerid(int perid) {
		this.perid = perid;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public int getSend() {
		return send;
	}
	public void setSend(int send) {
		this.send = send;
	}
	@Override
	public String toString() {
		return "Collect [perid=" + perid + ", openid=" + openid + ", send="
				+ send + "]";
	}
	

	
}
