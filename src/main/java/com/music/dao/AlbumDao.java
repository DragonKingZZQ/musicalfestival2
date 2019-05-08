package com.music.dao;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.music.entity.Album;


public interface AlbumDao {
			
	int deleteAll();
	
	List<Album> selectAll();
	
	int insertAlbum(String filePathXiangDui);

	
}
