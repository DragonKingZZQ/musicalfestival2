package com.music.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.music.entity.User;

public interface UserMapper {    //这个方法不再�?要实现，但是�?要准备映射语�?
	//User selectUserByName(@Param("name")String name);
	//登录
	List<User> selectLoginUser(@Param("username")String username,@Param("password")String password);
	//注册前查询数据是否已经存�?
	List<User> selectByUsername(@Param("username")String username);
	//注册
	int insertUser(@Param("username")String username,@Param("password")String password);
	
}
