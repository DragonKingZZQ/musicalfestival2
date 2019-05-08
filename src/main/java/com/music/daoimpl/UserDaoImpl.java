package com.music.daoimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.music.dao.UserDao;
import com.music.entity.User;
import com.music.mybatis.UserMapper;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Resource
	private UserMapper userMapper;
	
	public List<User> getUser(String username, String password) {
		
		return userMapper.selectLoginUser(username, password);
	}
	public int insertUser(String username, String password) {
		// TODO Auto-generated method stub
		return userMapper.insertUser(username, password);
	}
	public List<User> selectByUsername(String username) {
		// TODO Auto-generated method stub
		return userMapper.selectByUsername(username);
	}
	
	
	
	
}
