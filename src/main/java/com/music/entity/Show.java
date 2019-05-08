package com.music.entity;



public class Show {
	private Integer id;
	private String show_time;
	private Integer actor_id;
	private String stage_name;
	private String stage_time ;
	private Integer stage_order;
	private Integer count_num;
	private Actor actor;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getShow_time() {
		return show_time;
	}
	public void setShow_time(String show_time) {
		this.show_time = show_time;
	}
	public Integer getActor_id() {
		return actor_id;
	}
	public void setActor_id(Integer actor_id) {
		this.actor_id = actor_id;
	}
	
	public Integer getCount_num() {
		return count_num;
	}
	public void setCount_num(Integer count_num) {
		this.count_num = count_num;
	}
	public String getStage_name() {
		return stage_name;
	}
	public void setStage_name(String stage_name) {
		this.stage_name = stage_name;
	}
	public String getStage_time() {
		return stage_time;
	}
	public void setStage_time(String stage_time) {
		this.stage_time = stage_time;
	}
	public Integer getStage_order() {
		return stage_order;
	}
	public void setStage_order(Integer stage_order) {
		this.stage_order = stage_order;
	}
	public Actor getActor() {
		return actor;
	}
	public void setActor(Actor actor) {
		this.actor = actor;
	}
	@Override
	public String toString() {
		return "Show [id=" + id + ", show_time=" + show_time + ", actor_id="
				+ actor_id + ", stage_name=" + stage_name + ", stage_time="
				+ stage_time + ", stage_order=" + stage_order + ", count_num="
				+ count_num + ", actor=" + actor + "]";
	}
	
	
}
