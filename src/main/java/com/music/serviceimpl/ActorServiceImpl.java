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

import com.music.dao.ActorDao;
import com.music.entity.Actor;
import com.music.service.ActorService;



@Service
public class ActorServiceImpl implements ActorService{
	//依赖持久层：注入
	@Autowired
	private ActorDao actorDao;

	

	public String UploadActorPhoto(MultipartFile multipartFile,HttpServletRequest request) {
		
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
		String filepath = pro.get("actorpath").toString();
		String filepathmin = pro.get("actorImagemin").toString();	
		String serverPath = request.getSession().getServletContext().getRealPath("/");
		File root=new File(serverPath);
		String savePath = root.getParent();
		String realPath = savePath+filepath;
		File dir = new File(realPath);
		File dir2 = new File(savePath+filepathmin);	
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!dir2.exists()) {
            dir2.mkdirs();
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



	public int insertActor(Actor actor) {
		// TODO Auto-generated method stub
		return actorDao.insertActor(actor);
	}



	public int deleteActor(int id) {
		// TODO Auto-generated method stub
		return actorDao.deleteActor(id);
	}



	public int updateActor(Actor actor) {
		// TODO Auto-generated method stub
		return actorDao.modifiedActor(actor);
	}



	public List<Actor> selectList() {
		// TODO Auto-generated method stub
		return actorDao.selectActorList();
	}



	public Actor selectActorById(int id) {
		// TODO Auto-generated method stub
		return actorDao.selectActorById(id);
	}

	
}
