package com.music.service;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.music.entity.Banner;
import com.music.entity.User;


public interface BannerService{				
	int insertPhoto(@Param("photo_url")String photo_url);
	int updatePhoto_point_url(@Param("photo_point_url")String photo_point_url, int id);
	List<Banner> selectList();
	int deleteOne(@Param("id")int id);
	String SavePrdPic(MultipartFile multipartFile, int id,HttpServletRequest request);
}


