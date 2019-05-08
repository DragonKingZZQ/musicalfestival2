package com.music.serviceimpl;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.music.dao.LinkDao;
import com.music.entity.Link;
import com.music.service.LinkService;


@Service
public class LinkServiceImpl implements LinkService{
	//依赖持久层：注入
	@Autowired
	private LinkDao linkDao;

	
	public Link selectByLinktype(int link_type) {
		// TODO Auto-generated method stub
		return linkDao.selectByLinktype(link_type);
	}

	

	public int insertByLinktype(int link_type, String link_url) {
		// TODO Auto-generated method stub
		return linkDao.insertByLinktype(link_type, link_url);
	}

	public List<Link> selectList() {
		// TODO Auto-generated method stub
		return linkDao.selectLinkList();
	}

	public Map<String,String> manageLink(int link_type,String link_url){		
		//先进行查询，如果没有就进行插入
		Link link = linkDao.selectByLinktype(link_type);
		Map<String,String> map = new HashMap<String,String>();
		if(link==null) {
			int upres = linkDao.updateByLinkType(6, "");
			int insertres = linkDao.insertByLinktype(link_type, link_url);
			map.put("res", "插入成功");
		}else {
			int upres = linkDao.updateByLinkType(6, "");
			int updateres = linkDao.updateByLinkType(link_type, link_url);
			map.put("res", "修改成功！");
		}
		
		return map;
	}

	public Map<String,String> UploadBookHand(MultipartFile multipartFile,HttpServletRequest request,int link_type) {
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
		String filepath = pro.get("bookhandpath").toString();	
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
		Map<String,String> map = new HashMap<String,String>();
        try {
            multipartFile.transferTo(new File(realPath, filename));
            Link link = linkDao.selectByLinktype(link_type);            
            if(link==null) {
            	int upres = linkDao.updateByLinkType(7, "");
    			int insertres = linkDao.insertByLinktype(link_type, filePathXiangDui);
    			map.put("res", "插入pdf成功");
    		}else {
    			int upres = linkDao.updateByLinkType(7, "");
    			int updateres = linkDao.updateByLinkType(link_type, filePathXiangDui);
    			map.put("res", "更新pdf成功");
    		}
            map.put("pdfPath", filePathXiangDui);
           
        } catch (IOException e) {
            e.printStackTrace();
            
        }
        return map;
	}

	
	public int updateByLinkType(int link_type, String link_url) {
		// TODO Auto-generated method stub
		return linkDao.updateByLinkType(link_type, link_url);
	}



	public String selectByPDFOrHTML() {
		// TODO Auto-generated method stub
		return linkDao.selectByPDFOrHTML();
	}



	

	
	
	
}
