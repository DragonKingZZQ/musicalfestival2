package com.music.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.music.entity.Album;
import com.music.entity.HandBook;

public interface UploadService {

	String UploadAlbum(MultipartFile multipartFile, HttpServletRequest request);
	String Uploadticket(MultipartFile multipartFile,HttpServletRequest request);

	List<Album> getAlbumPicture();
	
	//上传乐迷手册
	String UploadHandBook(MultipartFile multipartFile, HttpServletRequest request);
	
	//得到所有乐迷手册
	List<HandBook> getHandBookPicture();
	
	//根据id进行删除
	int deleteHandBook(int id) ;
}
