package com.music.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.music.entity.Actor;
import com.music.entity.Album;
import com.music.entity.Banner;
import com.music.entity.User;

public interface AlbumMapper {    
	int deleteAll();
	
	List<Album> selectAll();
	
	int insertAlbum(@Param("album_url")String album_url);
	
	
}
