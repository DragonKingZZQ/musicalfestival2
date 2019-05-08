package com.music.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.music.entity.Collect;
import com.music.entity.Midi;
import com.music.entity.WXUser;
import com.music.entity.WXVo;

public interface WXService{

	int insertCollect(@Param("openid")String openid,@Param("perid")int perid);
	
	int deleteCollect(@Param("openid")String openid,@Param("perid")int perid);
	
	Collect selectCollect(@Param("openid")String openid,@Param("perid")int perid);
	
	int updateCollectSend(@Param("openid")String openid,@Param("perid")String perid);
	
	List<Collect> selecCollectByperid(@Param("perid")String perid);
	
	List<WXVo> wxselectBystage(@Param("openid")String openid,@Param("stage_time")String stage_time);
	
	List<WXVo> wxselectBytime(@Param("openid")String openid,@Param("stage_time")String stage_time,@Param("stage_name")String stage_name);

	List<WXVo> wxselectNextShow(@Param("openid")String openid,@Param("stage_time")String stage_time);
	
	//查询最近一天
	String wxselectMinTime(@Param("now_time")String now_time);
		
	//根据艺人查询所有艺人演出消息
	List<WXVo> wxselectByActor(@Param("openid")String openid,@Param("actor_id")int  actor_id);	
	
	//根据时间和用户id进行查询
	List<WXVo> wxselectByOpenIdAndDate(@Param("openid")String openid,@Param("stage_time")String stage_time);
	
	//授权
	int insertWXUserTwoValue(@Param("wx_user")WXUser wx_user);
	
	//迷笛公众号相关
	int insertMIDI(@Param("openid")String openid);
	
	Midi selectMidiByopenid(@Param("openid")String openid);
	
	int insertMIDITwoValue(@Param("midi")Midi midi);
	
	int updateMIDITwoValue(@Param("midi")Midi midi);
	
	Midi selectMidiByunionid(@Param("unionid")String unionid);
	
	List<Midi> findAllMidis();
	
	List<Midi> findAllNothaveUnionid();
	//小程序相关
	int insertWXUser(@Param("openid")String openid);
	
	WXUser selectOpenid(@Param("openid")String openid);
	
	int updateByOpenid(@Param("wx_user")WXUser wx_user);
	
	
	
}


