package com.music.dao;



import java.util.List;

import com.music.entity.User;

public interface UserDao {
			
	//登录 查询用户名和密码 用户信息
	List<User> getUser(String username,String password); 
	//注册前查询是否用户名已经存在
	List<User> selectByUsername(String username);
	//注册 存入用户名和密码
	int insertUser(String username,String password);
}
