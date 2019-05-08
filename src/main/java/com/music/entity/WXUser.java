package com.music.entity;



public class WXUser {
	private int id;
	private String openid;
	private String unionid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	@Override
	public String toString() {
		return "WXUser [id=" + id + ", openid=" + openid + ", unionid=" + unionid + "]";
	}

	
	
}
