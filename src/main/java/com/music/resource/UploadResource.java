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

import com.music.entity.Album;
import com.music.entity.HandBook;
import com.music.service.LinkService;
import com.music.service.UploadService;

@Controller
@RequestMapping(value = {"/uploadMany"})
public class UploadResource {
	//依赖服务层，自动注入	
	@Autowired
	private UploadService uploadService;
	@Autowired
	private LinkService linkService;
	
	 @ResponseBody            //上传图片
	 @RequestMapping(value = "/albumPicture.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public Map<String,String> UploadBannnerPhoto(@RequestParam(value = "file") MultipartFile multipartFile,HttpServletRequest request)  {
		 Map<String,String> map = new HashMap<String,String>();
		 String respath = uploadService.UploadAlbum(multipartFile, request);	 
		 map.put("img", respath);		 			 
		 return map;
	 }
	
	 @ResponseBody            //得到图片
	 @RequestMapping(value = "/getAlbumPicture.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public Map<String,String> getAlbumPicture()  {
		 Map<String,String> map = new HashMap<String,String>();
		 List<Album> respath = uploadService.getAlbumPicture();	
		 String res = respath.get(0).getAlbum_url();
		 map.put("img", res);		 			 
		 return map;
	 }
	 
	 @ResponseBody            //上传link_type 3 4 6我的订单  7官方支持
	 @RequestMapping(value = "/albumother.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public Map<String,String> Uploadticket(@RequestParam(value = "file") MultipartFile multipartFile,HttpServletRequest request,@RequestParam(value = "link_type")int link_type)  {
		 Map<String,String> map = new HashMap<String,String>();
		 System.out.println(link_type);
		 String respath = uploadService.Uploadticket(multipartFile, request);	 
		 linkService.updateByLinkType(link_type, respath);
		 map.put("img", respath);
		return map;		 			 
	 }
	 //手册
	 
	 
	 @ResponseBody            //上传图片
	 @RequestMapping(value = "/handBookPicture.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public Map<String,String> handBookPicture(@RequestParam(value = "file") MultipartFile multipartFile,HttpServletRequest request)  {
		 Map<String,String> map = new HashMap<String,String>();
		 String respath = uploadService.UploadHandBook(multipartFile, request); 
		 map.put("img", respath);		 			 
		 return map;
	 }
	 
	 
	 @ResponseBody            //得到手册
	 @RequestMapping(value = "/getHandBookPicture.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public List<HandBook> getHandBookPicture()  {
		 Map<String,String> map = new HashMap<String,String>();
		 List<HandBook> respath = uploadService.getHandBookPicture();	 			 
		 return respath;
	 }
	 
	 
	 @ResponseBody            //删除手册
	 @RequestMapping(value = "/deleteHandBookPicture.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public Map<String,String> deleteHandBookPicture(@RequestParam(value = "id") int id)  {
		 Map<String,String> map = new HashMap<String,String>();
		 int res = uploadService.deleteHandBook(id); 
		 if(res>0) {
			 map.put("res","删除成功！");
		 }else {
			 map.put("res", "删除失败！");
		 }
		 return map;
	 }
	
}
