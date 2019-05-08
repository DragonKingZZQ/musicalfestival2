package com.music.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.music.entity.Play;


public interface PlayMapper {    
	int insertPhoto(@Param("photo_url")String photo_url);
	int updatePhoto_point_url(@Param("photo_point_url")String photo_point_url,@Param("id")int id);
	List<Play> selectList();
	int deleteOne(@Param("id")int id);
	Play selectOne(@Param("id")int id);
	int updatePhoto_url(@Param("id")int id);
	int updatePhoto_url(@Param("photo_url")String photo_url, @Param("id")int id);
}
