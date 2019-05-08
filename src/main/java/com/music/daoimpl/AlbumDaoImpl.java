package com.music.daoimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.music.dao.AlbumDao;
import com.music.dao.UserDao;
import com.music.entity.Album;
import com.music.entity.User;
import com.music.mybatis.AlbumMapper;
import com.music.mybatis.UserMapper;

@Repository
public class AlbumDaoImpl implements AlbumDao{
	
	@Resource
	private AlbumMapper albumMapper;

	public int deleteAll() {
		// TODO Auto-generated method stub
		return albumMapper.deleteAll();
	}

	public List<Album> selectAll() {
		// TODO Auto-generated method stub
		return albumMapper.selectAll();
	}

	

	public int insertAlbum(String album_url) {
		// TODO Auto-generated method stub
		return albumMapper.insertAlbum(album_url);
	}

	
	
	
}
