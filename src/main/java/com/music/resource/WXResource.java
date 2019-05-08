package com.music.resource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.music.dao.WXvoUserDao;
import com.music.entity.MapWxVo;
import com.music.entity.Midi;
import com.music.entity.Show;
import com.music.entity.WXUser;
import com.music.entity.WXVo;
import com.music.service.ShowService;
import com.music.service.WXService;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;


@Controller
@RequestMapping(value = {"/WXActor"})
public class WXResource {
	//依赖服务层，自动注入	
	@Autowired
	private WXService xwService;
	
	@Autowired
	private ShowService showService;
	
	@Autowired
    private WxMpService wxMpService;
	
	
	@ResponseBody            
	@RequestMapping(value = "/wxselectAllBystage.do", method = {RequestMethod.POST, RequestMethod.GET})
	public List<WXVo> wxselectBystage(@RequestParam(value = "openid")String openid,@RequestParam(value = "stage_time")String stage_time){
		List<WXVo> wx = xwService.wxselectBystage(openid, stage_time);
		return wx;
	}
	
	@ResponseBody            
	@RequestMapping(value = "/wxselectAllBytime.do", method = {RequestMethod.POST, RequestMethod.GET})
	public List<WXVo> wxselectBytime(@RequestParam(value = "openid")String openid,@RequestParam(value = "stage_time",required=false)String stage_time,@RequestParam(value = "stage_name",required=false)String stage_name){
		if(stage_time == null || stage_name == null) {
			Date day=new Date();  
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
			String now_time = df.format(day); 
			String stage_time2 = xwService.wxselectMinTime(now_time);
			//System.out.println(now_time+"st"+stage_time2);
			List<WXVo> wx = xwService.wxselectNextShow(openid, stage_time2);
			return wx;
		}else {
			List<WXVo> wx = xwService.wxselectBytime(openid, stage_time, stage_name);
			return wx;
		}
		
	}
	
	@ResponseBody            
	@RequestMapping(value = "/wxselectByOpenIdAndDate.do", method = {RequestMethod.POST, RequestMethod.GET})
	public List<MapWxVo> wxselectByOpenIdAndDate(@RequestParam(value = "openid")String openid,@RequestParam(value = "stage_time")String stage_time){
		
		List<WXVo> wx = xwService.wxselectByOpenIdAndDate(openid, stage_time);
		System.out.println(wx.toString());
		List<MapWxVo> mapWxVos=new ArrayList<>();
		MapWxVo mapWxVo=new MapWxVo();
		Map<String,String> stagename=new HashMap<String,String>();
		Map<String,List<WXVo>> maps = new HashMap<String,List<WXVo>>();
		List<WXVo> WXVo=new ArrayList<>();
		System.out.println(wx.size());
		String stage_name=wx.get(0).getStage_name();
		stagename.put("name", stage_name);
		
		//给maps赋值根据stage_name的变化
		int j=0;
		try {
			for(int i=0;i<wx.size();i=j) {
				//判断舞台name
				if(stage_name.equals(wx.get(i).getStage_name())) {
					//如果相同
					for(j=i;j<wx.size();j++) {
//					System.out.println(stage_name);
//					System.out.println(wx.get(j).getStage_name());
//					System.out.println(wx.get(j).getActor_id());
						if(stage_name.equals(wx.get(j).getStage_name())&&wx.get(j).getActor_id()!=null) {
							WXVo.add(wx.get(j));
						}
						if(!stage_name.equals(wx.get(j).getStage_name())) {
							stage_name=wx.get(j).getStage_name();
							break;
						}
					}
					maps.put("list", WXVo);
					mapWxVo.setName(stagename);
					mapWxVo.setList(maps);
					mapWxVos.add(mapWxVo);
					mapWxVo=new MapWxVo();
					stagename=new HashMap<String,String>();
					maps = new HashMap<String,List<WXVo>>();
					WXVo=new ArrayList<>();
					maps.clear();
					stagename.clear();
					WXVo.clear();
					stagename.put("name", stage_name);
					
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("无");
			return mapWxVos;
		}	
		System.out.println("返回值"+mapWxVos.toString());
			return mapWxVos;
		
		
	}
	
	@ResponseBody            
	@RequestMapping(value = "/wxselectAllByActorId.do", method = {RequestMethod.POST, RequestMethod.GET})
	public List<WXVo> wxselectByActor(@RequestParam(value = "openid")String openid,@RequestParam(value = "actor_id")int actor_id){		
			List<WXVo> wx = xwService.wxselectByActor(openid, actor_id);
			return wx;
	
	}
	
	//用户添加收藏
	@ResponseBody            
	@RequestMapping(value = "/collect.do", method = {RequestMethod.POST, RequestMethod.GET})
	public Map<String,String> insertCollect1(@RequestParam(value = "openid")String openid,@RequestParam(value = "perid")int perid){
		Map<String,String> map = new HashMap<String,String>();
		int res = xwService.insertCollect(openid, perid);
		Show show= showService.selectTimeById(perid);
		String show_date = show.getStage_time();
		String show_time = show.getShow_time();
		String[] show_times = show_time.split("-");
		String time = show_date+show_times[0];
		//System.out.println(time);
		DateFormat df = new SimpleDateFormat("yyyy-MM-ddhh:mm:ss");
		try {
			Date dt1 = df.parse(time); //年月日演出时间
			//System.out.println(dt1.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		//System.out.println(show_date+"show_date"+show_time+"show_time");
		if(res>0) {
			map.put("res", "收藏成功");
		}else {
			map.put("res", "收藏失败");
		}
		
		return map;
	 }
	
	//用户取消收藏
	@ResponseBody            
	@RequestMapping(value = "/notcollect.do", method = {RequestMethod.POST, RequestMethod.GET})
	public Map<String,String> notcollect(@RequestParam(value = "openid")String openid,@RequestParam(value = "perid")int perid){
		Map<String,String> map = new HashMap<String,String>();
		int res = xwService.deleteCollect(openid, perid);
		if(res>0) {
			map.put("res", "取消收藏成功");
		}else {
			map.put("res", "取消收藏失败");
		}		
		return map;
	 }
	
	
	
		
}
