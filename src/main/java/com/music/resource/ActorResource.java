package com.music.resource;



import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.music.entity.Actor;
import com.music.service.ActorService;
import com.music.service.ShowService;


@Controller
@RequestMapping(value = {"/uploadActor"})
public class ActorResource {
	//依赖服务层，自动注入	
	@Autowired
	private ActorService actorService;
	@Autowired
	private ShowService showService;
	
	 @ResponseBody            //上传图片
	 @RequestMapping(value = "/actorPicture.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public Map<String,String> UploadBannnerPhoto(@RequestParam(value = "file") MultipartFile multipartFile,HttpServletRequest request)  {
		 Map<String,String> map = new HashMap<String,String>();
		 String respath = actorService.UploadActorPhoto(multipartFile, request);	 
		 System.out.println("路径="+request.getServletContext().getRealPath(""));
		 File root=new File(request.getServletContext().getRealPath(""));
		 String savePath = root.getParent();
		 String url=savePath+respath;
		 String [] t=respath.split("/");
		 System.out.println("t[t.length-1]="+t[t.length-1]);
		 String [] s=t[t.length-1].split("\\.");
		 if (!s[s.length-1].equals("jpg")) {
		 String nameString="";
		 for (int i = 0; i < s.length-1; i++) {
			 nameString+=s[i];
			 if (i+1<s.length-1) {
				 nameString+=".";
			}
		}
		 System.out.println(nameString);
		 try {
			 /*
			  * Thumbnails.of("原图文件的路径") 
		        .scale(1f) 
		        .outputQuality(0.5f) 
		        .toFile("压缩后文件的路径");
			  * */
			Thumbnails.of(url) 
			    .scale(0.5f) 
			    .outputQuality(0.5f).outputFormat("jpg")
			    .toFile(savePath+"/actorImagemin/"+nameString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else{
			try {
				 /*
				  * Thumbnails.of("原图文件的路径") 
			        .scale(1f) 
			        .outputQuality(0.5f) 
			        .toFile("压缩后文件的路径");
				  * */
				Thumbnails.of(url) 
				    .scale(0.5f) 
				    .outputQuality(0.5f)
				    .toFile(savePath+"/actorImagemin/"+url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 System.out.println("respath="+respath+"|"+"savePath="+savePath);
		 map.put("img", respath);		 			 
		 return map;
	 }
	
	 @ResponseBody         //插入艺人信息
	 @RequestMapping(value = "/insertActor.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public Map<String,String> insertActor(Actor actor)  {
		 Map<String,String> map = new HashMap<String,String>();
		 int res = actorService.insertActor(actor);
	     if(res>0) {
	    	 map.put("res", "success");
	    	 return map;
	     }
	     map.put("res", "error");
		 return map;
	 }
	 
	 @ResponseBody         //修改艺人信息
	 @RequestMapping(value = "/updateActor.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public Map<String,String> updateActor(Actor actor)  {
		 Map<String,String> map = new HashMap<String,String>();
		 int res = actorService.updateActor(actor);
	     if(res>0) {
	    	 map.put("res", "success");
	    	 return map;
	     }
	     map.put("res", "error");
		 return map;
	 }
	 
	 @ResponseBody    //得到所有的列表
	 @RequestMapping(value = "/actorList.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public List<Actor> selectList()  {
	     List<Actor> res = actorService.selectList();	     
		 return res;
	 }
		
	//根据艺人id进行删除   以及删除他的演出
	 @ResponseBody    
	 @RequestMapping(value = "/delActorById.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public Map<String,String> delActorById(@RequestParam(value = "id")int id)  {
	     int res = actorService.deleteActor(id) ;   
	     showService.deleteByactorid(id);
	     Map<String,String> map = new HashMap<String,String>();
	     if(res>0) {
			 map.put("res", "删除成功");
		 }else {
			 map.put("res", "删除失败");
		 }
	     return map;
	 }
	 //根据艺人id进行查询
	 @ResponseBody    
	 @RequestMapping(value = "/selActorById.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public Actor selActorById(@RequestParam(value = "id")int id)  {
	     Actor res = actorService.selectActorById(id);	     
	     return res;
	 }
}
