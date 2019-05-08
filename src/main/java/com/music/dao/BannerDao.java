package com.music.dao;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.music.entity.Banner;
import com.music.entity.User;

public interface BannerDao {
	int insertPhoto(@Param("photo_url")String photo_url);
	int updatePhoto_point_url(@Param("photo_point_url")String photo_point_url, int id);
	List<Banner> selectList();
	int deleteOne(@Param("id")int id);
	Banner selectOne(@Param("id")int id);
	//int updatePhoto_url(@Param("id")int id);
	int updatePhoto_url(@Param("photo_url")String photo_url, @Param("id")int id);
}
