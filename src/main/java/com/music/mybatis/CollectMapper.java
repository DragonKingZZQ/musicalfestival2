package com.music.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.music.entity.Collect;

public interface CollectMapper { 
	
	int insertCollect(@Param("openid")String openid,@Param("perid")int perid);
	
	int deleteCollect(@Param("openid")String openid,@Param("perid")int perid);
	
	Collect selectCollect(@Param("openid")String openid,@Param("perid")int perid);
	
	int updateCollectSend(@Param("openid")String openid,@Param("perid")String perid);
	
	List<Collect> selecCollectByperid(@Param("perid")String perid);
	
}
