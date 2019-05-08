package com.music.daoimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.music.dao.BannerDao;
import com.music.dao.UserDao;
import com.music.entity.Banner;
import com.music.entity.User;
import com.music.mybatis.BannerMapper;
import com.music.mybatis.UserMapper;

@Repository
public class BannerDaoImpl implements BannerDao{
	
	@Resource
	private BannerMapper bannerMapper;

	public int insertPhoto(String photo_url) {
		// TODO Auto-generated method stub
		return bannerMapper.insertPhoto(photo_url);
	}

	public int updatePhoto_point_url(String photo_point_url,int id) {
		// TODO Auto-generated method stub
		return bannerMapper.updatePhoto_point_url(photo_point_url,id);
	}

	public List<Banner> selectList() {
		// TODO Auto-generated method stub
		return bannerMapper.selectList();
	}

	public int deleteOne(int id) {
		// TODO Auto-generated method stub
		return bannerMapper.deleteOne(id);
	}

	public Banner selectOne(int id) {
		// TODO Auto-generated method stub
		return bannerMapper.selectOne(id);
	}

	public int updatePhoto_url(String filename, int id) {
		// TODO Auto-generated method stub
		return bannerMapper.updatePhoto_url(filename,id);
	}

	
	
	
	
	
	
	
}
