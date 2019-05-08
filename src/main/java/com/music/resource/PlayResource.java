package com.music.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.music.entity.Play;
import com.music.service.PlayService;

@Controller
@RequestMapping(value = {"/upload"})
public class PlayResource {
	//依赖服务层，自动注入	
	@Autowired
	private PlayService playService;
	
	 @ResponseBody            //上传播放列表图片
	 @RequestMapping(value = "/playPicture.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public Map<String,String> UploadBannnerPhoto(@RequestParam(value = "file") MultipartFile multipartFile,int id,HttpServletRequest request)  {
		 Map<String,String> map = new HashMap<String,String>();
		 String respath = playService.SavePrdPic(multipartFile,id,request);		 
		 map.put("img", respath);		 
		 map.put("img", respath);	 
		 return map;
	 }
	
	 @ResponseBody         //更新图片链接地址
	 @RequestMapping(value = "/playUrl.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public Map<String,String> insertBannerUrl(@RequestParam(value = "photo_point_url")String photo_point_url,
			 @RequestParam(value = "id")int id)  {
		 Map<String,String> map = new HashMap<String,String>();
		 int res = playService.updatePhoto_point_url(photo_point_url,id);
	     if(res>0) {
	    	 map.put("res", "success");
	    	 return map;
	     }
	     map.put("res", "error");
		 return map;
	 }
	 
	 @ResponseBody    //得到所有的列表
	 @RequestMapping(value = "/playList.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public List<Play> selectList()  {
	     List<Play> res = playService.selectList();	     
		 return res;
	 }

}
