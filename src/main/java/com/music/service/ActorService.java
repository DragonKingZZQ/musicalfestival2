package com.music.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.music.entity.Actor;

public interface ActorService{

	String UploadActorPhoto(MultipartFile multipartFile,HttpServletRequest request);
				
	int insertActor(Actor actor);
	
	int deleteActor(int id);
	
	int updateActor(Actor actor);
	
	List<Actor> selectList();
	
	Actor selectActorById(@Param("id")int id);
}


