package com.music.serviceimpl;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.music.dao.AlbumDao;
import com.music.dao.HandBookDao;
import com.music.entity.Album;
import com.music.entity.HandBook;
import com.music.service.UploadService;



@Service
public class UploadServiceImpl implements UploadService{
	//依赖持久层：注入
	@Autowired
	private AlbumDao albumDao;
	
	@Autowired
	private HandBookDao handBookDao;

	

	public String UploadAlbum(MultipartFile multipartFile,HttpServletRequest request) {
		
		URL url = this.getClass().getClassLoader().getResource("uploadPath.properties");
		Properties pro = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream(url.getPath());
			pro.load(in);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String filepath = pro.get("albumpath").toString();	
		String serverPath = request.getSession().getServletContext().getRealPath("/");
		File root=new File(serverPath);
		String savePath = root.getParent();
		String realPath = savePath+filepath;
		File dir = new File(realPath);			
        if (!dir.exists()) {
            dir.mkdirs();
        }
		//System.out.println(realPath+"realPath");
		String alias = UUID.randomUUID().toString().replaceAll("-", "");
		String filename = alias+multipartFile.getOriginalFilename();
		String filePathXiangDui = filepath+filename;	
		//System.out.println(filename+"filename");
		
        try {
            multipartFile.transferTo(new File(realPath, filename));
            int delres = albumDao.deleteAll();
            int insertres = albumDao.insertAlbum(filePathXiangDui);
            if(insertres>0) {
            	return filePathXiangDui;
            }else {
            	return "error";
            }
         
        } catch (IOException e) {
            e.printStackTrace();
            
        }
        return "error";
	}

	public String Uploadticket(MultipartFile multipartFile,HttpServletRequest request) {
		
		URL url = this.getClass().getClassLoader().getResource("uploadPath.properties");
		Properties pro = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream(url.getPath());
			pro.load(in);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String filepath = pro.get("albumpath").toString();	
		String serverPath = request.getSession().getServletContext().getRealPath("/");
		File root=new File(serverPath);
		String savePath = root.getParent();
		String realPath = savePath+filepath;
		File dir = new File(realPath);			
        if (!dir.exists()) {
            dir.mkdirs();
        }
		//System.out.println(realPath+"realPath");
		String alias = UUID.randomUUID().toString().replaceAll("-", "");
		String filename = alias+multipartFile.getOriginalFilename();
		String filePathXiangDui = filepath+filename;	
		//System.out.println(filename+"filename");
		
        try {
            multipartFile.transferTo(new File(realPath, filename));
         return filePathXiangDui;

        } catch (IOException e) {
            e.printStackTrace();
            
        }
        return "error";
	}

	public List<Album> getAlbumPicture() {
		// TODO Auto-generated method stub
		return albumDao.selectAll();
	}



	@Override
	public String UploadHandBook(MultipartFile multipartFile, HttpServletRequest request) {
		URL url = this.getClass().getClassLoader().getResource("uploadPath.properties");
		Properties pro = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream(url.getPath());
			pro.load(in);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String filepath = pro.get("handbookpath").toString();	
		String serverPath = request.getSession().getServletContext().getRealPath("/");
		File root=new File(serverPath);
		String savePath = root.getParent();
		String realPath = savePath+filepath;
		File dir = new File(realPath);			
        if (!dir.exists()) {
            dir.mkdirs();
        }
		//System.out.println(realPath+"realPath");
		String alias = UUID.randomUUID().toString().replaceAll("-", "");
		String filename = alias+multipartFile.getOriginalFilename();
		String filePathXiangDui = filepath+filename;	
		//System.out.println(filename+"filename");
		
        try {
            multipartFile.transferTo(new File(realPath, filename));
            int insertres = handBookDao.insertHandBook(filePathXiangDui);
            if(insertres>0) {
            	return filePathXiangDui;
            }else {
            	return "error";
            }
         
        } catch (IOException e) {
            e.printStackTrace();
            
        }
        return "error";
	}



	@Override
	public List<HandBook> getHandBookPicture() {
		// TODO Auto-generated method stub
		return handBookDao.selectAll();
	}



	@Override
	public int deleteHandBook(int id) {
		// TODO Auto-generated method stub
		return handBookDao.deleteById(id);
	}



	
	
	
}
