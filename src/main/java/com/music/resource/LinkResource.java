package com.music.resource;

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

import com.music.entity.Link;
import com.music.service.LinkService;
import com.music.util.LinkEnum;


@Controller
@RequestMapping(value = {"/LinkActor"})
public class LinkResource {
	//依赖服务层，自动注入	
	@Autowired
	private LinkService linkService;
	
	 @ResponseBody            
	 @RequestMapping(value = "/insertlink.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public Map<String,String> LinkManager(@RequestParam(value = "link_type") int link_type,@RequestParam(value = "link_url") String link_url)  {
		 Map<String,String> map = linkService.manageLink(link_type, link_url);	 		 			 
		 return map;
	 }
	 
	 @ResponseBody            
	 @RequestMapping(value = "/insertbookhandpdf.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public Map<String,String> insertbookhand(@RequestParam(value = "file") MultipartFile multipartFile,HttpServletRequest request,@RequestParam(value = "link_type") int link_type)  {
		 Map<String,String> map = linkService.UploadBookHand(multipartFile, request,link_type); 		 			 
		 return map;
	 }
	
	 
	 @ResponseBody            
	 @RequestMapping(value = "/selectList.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public List<Link> selectList()  {
		 List<Link> links= linkService.selectList();
		 for(int i=0;i<links.size();i++) {
			 links.get(i).setLink_type_introduce(LinkEnum.getAccountType(links.get(i).getLink_type()).getMessage());
		 }
		 return links;
	 }
	
	 @ResponseBody    //查找普通链接        
	 @RequestMapping(value = "/selectLinkOne.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public String selectOne(@RequestParam(value = "link_type") int link_type)  {		
		Link link= linkService.selectByLinktype(link_type);
		String res = link.getLink_url(); 
		return res;
	 }	
	
	 @ResponseBody      //查找乐迷手册      
	 @RequestMapping(value = "/selectHandBook.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public String selectHandBook()  {
		 Link link = linkService.selectByLinktype(6);
		 if("".equals(link.getLink_url())) { //pdf版为空
			 Link link1 = linkService.selectByLinktype(7);
			 return link1.getLink_url();
		 }else {
			 return link.getLink_url();
		 }
	 }	
}
