package com.music.daoimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;


import com.music.dao.PlayDao;

import com.music.entity.Banner;
import com.music.entity.Play;
import com.music.mybatis.PlayMapper;


@Repository
public class PlayDaoImpl implements PlayDao{
	
	@Resource
	private PlayMapper playMapper;

	public int insertPhoto(String photo_url) {
		// TODO Auto-generated method stub
		return playMapper.insertPhoto(photo_url);
	}

	public int updatePhoto_point_url(String photo_point_url,int id) {
		// TODO Auto-generated method stub
		return playMapper.updatePhoto_point_url(photo_point_url,id);
	}

	public List<Play> selectList() {
		// TODO Auto-generated method stub
		return playMapper.selectList();
	}

	public int deleteOne(int id) {
		// TODO Auto-generated method stub
		return playMapper.deleteOne(id);
	}

	public Play selectOne(int id) {
		// TODO Auto-generated method stub
		return playMapper.selectOne(id);
	}

	public int updatePhoto_url(String filename, int id) {
		// TODO Auto-generated method stub
		return playMapper.updatePhoto_url(filename,id);
	}

	
	
	
	
	
	
	
}
