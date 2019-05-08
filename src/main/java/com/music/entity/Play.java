package com.music.entity;

public class Play {
	private Integer id;
	private String photo_url;
	private String photo_point_url;
	
	public Play() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhoto_url() {
		return photo_url;
	}
	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}
	public String getPhoto_point_url() {
		return photo_point_url;
	}
	public void setPhoto_point_url(String photo_point_url) {
		this.photo_point_url = photo_point_url;
	}
	public Play(Integer id, String photo_url, String photo_point_url) {
		super();
		this.id = id;
		this.photo_url = photo_url;
		this.photo_point_url = photo_point_url;
	}
	
}
