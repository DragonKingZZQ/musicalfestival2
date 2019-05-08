package com.music.daoimpl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.music.dao.CollectDao;
import com.music.entity.Collect;
import com.music.mybatis.CollectMapper;

@Repository
public class CollectDaoImpl implements CollectDao{
	
	@Resource
	private CollectMapper collectMapper;

	public int insertCollect(String openid, int perid) {
		// TODO Auto-generated method stub
		return collectMapper.insertCollect(openid, perid);
	}

	public int deleteCollect(String openid, int perid) {
		// TODO Auto-generated method stub
		return collectMapper.deleteCollect(openid, perid);
	}

	public Collect selectCollect(String openid, int perid) {
		// TODO Auto-generated method stub
		return collectMapper.selectCollect(openid, perid);
	}

	@Override
	public int updateCollectSend(String openid, String perid) {
		// TODO Auto-generated method stub
		return collectMapper.updateCollectSend(openid, perid);
	}

	@Override
	public List<Collect> selecCollectByperid(String perid) {
		// TODO Auto-generated method stub
		return collectMapper.selecCollectByperid(perid);
	}

	
	
}
