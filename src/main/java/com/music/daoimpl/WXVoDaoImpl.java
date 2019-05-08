package com.music.daoimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import com.music.dao.WXvoUserDao;
import com.music.entity.WXVo;
import com.music.mybatis.WXVoMapper;

@Repository
public class WXVoDaoImpl implements WXvoUserDao{
	
	@Resource
	private WXVoMapper wxvoMapper;

	public List<WXVo> wxselectBystage(String openid, String stage_time) {
		// TODO Auto-generated method stub
		return wxvoMapper.wxselectBystage(openid, stage_time);
	}

	public List<WXVo> wxselectBytime(String openid, String stage_time, String stage_name) {
		// TODO Auto-generated method stub
		return wxvoMapper.wxselectBytime(openid, stage_time, stage_name);
	}

	@Override
	public List<WXVo> wxselectNextShow(String openid, String stage_time) {
		// TODO Auto-generated method stub
		return wxvoMapper.wxselectNextShow(openid, stage_time);
	}

	@Override
	public String wxselectMinTime(String now_time) {
		// TODO Auto-generated method stub
		return wxvoMapper.wxselectMinTime(now_time);
	}

	@Override
	public List<WXVo> wxselectByActor(String openid, int actor_id) {
		// TODO Auto-generated method stub
		return wxvoMapper.wxselectByActor(openid, actor_id);
	}

	@Override
	public List<WXVo> wxselectByOpenIdAndDate(String openid, String stage_time) {
		// TODO Auto-generated method stub
		return wxvoMapper.wxselectByOpenIdAndDate(openid, stage_time);
	}

	

	
	
}
