package com.music.entity;

import java.util.List;
import java.util.Map;

/*
 * 
 * Map<String,String> Date=new HashMap<String,String>();
		
		Map<String,List<WXVo>>
 * */
public class MapWxVo {
	private Map<String,String> name;
	private Map<String,List<WXVo>> list;
	public Map<String, String> getName() {
		return name;
	}
	public void setName(Map<String, String> name) {
		this.name = name;
	}
	public Map<String, List<WXVo>> getList() {
		return list;
	}
	public void setList(Map<String, List<WXVo>> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "MapWxVo [name=" + name + ", list=" + list + "]";
	}
	
	

}
