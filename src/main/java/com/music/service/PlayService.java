package com.music.service;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;
import com.music.entity.Play;


public interface PlayService{				
	int insertPhoto(@Param("photo_url")String photo_url);
	int updatePhoto_point_url(@Param("photo_point_url")String photo_point_url, int id);
	List<Play> selectList();
	int deleteOne(@Param("id")int id);
	String SavePrdPic(MultipartFile multipartFile, int id,HttpServletRequest request);
}


