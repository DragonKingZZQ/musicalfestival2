package com.music.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.music.entity.Link;

public interface LinkService{

	int updateByLinkType(@Param("link_type")int link_type,@Param("link_url")String link_url);
	
	Link selectByLinktype(@Param("link_type")int link_type);
	
	int insertByLinktype(@Param("link_type")int link_type,@Param("link_url")String link_url);
				
	List<Link> selectList();

	Map<String,String> manageLink(int link_type,String link_url);
	
	Map<String,String> UploadBookHand(MultipartFile multipartFile,HttpServletRequest request,int link_type);
	
	String selectByPDFOrHTML();
}


