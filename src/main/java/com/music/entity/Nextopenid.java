package com.music.entity;

public class Nextopenid {
private String id;
private String next_openid;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getNext_openid() {
	return next_openid;
}
public void setNext_openid(String next_openid) {
	this.next_openid = next_openid;
}
@Override
public String toString() {
	return "Nextopenid [id=" + id + ", next_openid=" + next_openid + "]";
}

}
