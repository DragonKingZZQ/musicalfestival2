package com.music.service;

import java.util.List;

import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;

import org.apache.ibatis.annotations.Param;

import com.music.entity.Nextopenid;



public interface WXMessageService{

	public void sendMessage(String userOpenId);
	public void sendMessage(String userOpenId, List<WxMpTemplateData> data);
	
	//查询全部nextopendi
	List<Nextopenid> findAllnextopenid();
//修改nextopendi
	int updateNextopenidById(@Param("id")String id);
//添加nextopenid
	int insertNextopenid(@Param("Nextopenid")Nextopenid nextopenid);
	
}


