package com.music.mybatis;

import org.apache.ibatis.annotations.Param;
import com.music.entity.WXUser;

public interface WXUserMapper { 
	
	int insertWXUser(@Param("openid")String openid);
	
	WXUser selectOpenid(@Param("openid")String openid);
	
	int insertWXUserTwoValue(@Param("wx_user")WXUser wx_user);
	
	int updateByOpenid(@Param("wx_user")WXUser wx_user);
}
