package com.music.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.dao.UserDao;
import com.music.entity.User;
import com.music.service.UserService;
import com.music.util.CookieUtils;

@Service
public class UserServiceImpl implements UserService{
	//依赖持久层：注入
	@Autowired
	private UserDao userDao;

	private static String TT_TOKEN = "TT_TOKEN";
	
	public Map<String,String> login(String username, String password,HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> map = new HashMap<String,String>();		
		//System.out.println(username+""+password);
		List<User> userlist = userDao.getUser(username, password);
		//System.out.println(userlist.get(0).getPassword()+"username"+password+"password");
		if(userlist.size()==0) {
			map.put("msg", "用户名或密码错误");
			map.put("code", "500");
			return map;
		}
		User user = userlist.get(0);
		//System.out.println(DigestUtils.md5Hex(password.getBytes())+"啥玩�?");
		
		//生成token
		UUID uuid = UUID.randomUUID();
		String token = uuid.toString();
		CookieUtils.setCookie(request, response,TT_TOKEN, token);
		//返回token
		map.put("msg", token);
		map.put("code", "200");
		return map ;
	}

	public User checkInfo(String data, int type) {
		// TODO Auto-generated method stub
		return null;
	}


	public Map<Integer,String> registe(User user) {
		Map<Integer,String> map = new HashMap<Integer,String>();
		//密码进行md5加密
		String password = DigestUtils.md5Hex(user.getPassword().getBytes());
		user.setPassword(password);
		//查询用户名是否已经存�?
		List<User> list = userDao.selectByUsername(user.getUsername());
		if(list.size() > 0) {
			map.put(1, "此用户名已经有人注册�?");
			return map;
		}
		int i = userDao.insertUser(user.getUsername(), password);
		if(i != 1) {
			map.put(2, "注册失败");
			return map;
		}
		map.put(3, "注册成功");
		return map;
	}

	

	
	

	
	
}
