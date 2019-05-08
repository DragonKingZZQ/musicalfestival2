package com.music.resource;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.music.entity.User;
import com.music.service.UserService;
@Controller
public class UserResource {
	//依赖服务层，自动注入	
	@Autowired
	private UserService userService;
		

	@RequestMapping("/dologin.do")
	@ResponseBody
	public Map<String,String>  dologin(User user,HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> map = userService.login(user.getUsername(),user.getPassword(),request,response);
		return map;
	}
	
	
}
