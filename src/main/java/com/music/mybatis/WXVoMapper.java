package com.music.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.music.entity.WXVo;

public interface WXVoMapper { 
	
	List<WXVo> wxselectBystage(@Param("openid")String openid,@Param("stage_time")String stage_time);
	
	List<WXVo> wxselectBytime(@Param("openid")String openid,@Param("stage_time")String stage_time,@Param("stage_name")String stage_name);
	
	//查询最近一天
	String wxselectMinTime(@Param("now_time")String now_time);
	
	// 根据用户id、最近演出日期、 查询演出详细信息以及用户是否关注  演出时间升序排序-->
	List<WXVo> wxselectNextShow(@Param("openid")String openid,@Param("stage_time")String stage_time);

	//根据艺人查询所有艺人演出消息
	List<WXVo> wxselectByActor(@Param("openid")String openid,@Param("actor_id")int  actor_id);
	
	//根据时间和用户id进行查询
	List<WXVo> wxselectByOpenIdAndDate(@Param("openid")String openid,@Param("stage_time")String stage_time);
}
