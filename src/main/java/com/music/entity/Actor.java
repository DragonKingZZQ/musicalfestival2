package com.music.entity;



public class Actor {
	private Integer id;
	private String actor_name;	
	private String actor_photo;
	private String actor_photo_min;
	private String introduce;
	
	public String getActor_photo_min() {
		return actor_photo_min;
	}
	public void setActor_photo_min(String actor_photo_min) {
		this.actor_photo_min = actor_photo_min;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getActor_name() {
		return actor_name;
	}
	public void setActor_name(String actor_name) {
		this.actor_name = actor_name;
	}
	
	
	public String getActor_photo() {
		return actor_photo;
	}
	public void setActor_photo(String actor_photo) {
		this.actor_photo = actor_photo;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	@Override
	public String toString() {
		return "Actor [id=" + id + ", actor_name=" + actor_name
				+ ", actor_photo=" + actor_photo + ", actor_photo_min="
				+ actor_photo_min + ", introduce=" + introduce + "]";
	}
	
	
	
}
