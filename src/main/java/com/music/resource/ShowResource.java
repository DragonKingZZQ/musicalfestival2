package com.music.resource;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.music.entity.Show;
import com.music.service.ShowService;

@Controller
@RequestMapping(value = {"/stage"})
public class ShowResource {
	//依赖服务层，自动注入	
	@Autowired
	private ShowService showService;
	
	 @ResponseBody            //查询所有日期
	 @RequestMapping(value = "/selectDates.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public List<String> selectDates()  {
		 Map<String,List> map = new HashMap<String,List>();
		 List<String> list = showService.selectDateList();
		 if(list == null) {
			 return null;
		 }else {
			 return list;
		 }		 	 			 
	 }
	
	 @ResponseBody         //添加舞台数据
	 @RequestMapping(value = "/addStage.do", method = {RequestMethod.POST})
	 public Map<String,String> addStage(@RequestBody(required = true)List<Show> stagelist)  {
		 Map<String,String> map = showService.addStage(stagelist);		 
	     return map;
	 }
	 
	
	 @ResponseBody         //根据日期查询所有舞台 pc端
	 @RequestMapping(value = "/selStageByDate.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public List<Show> selStageByDate(@RequestParam(value = "stage_time")String stage_time)  {
		 Map<String,String> map = new HashMap<String,String>();
		 List<Show> stages = showService.selectStageListByDate(stage_time);
	     return stages;
	 }
	 
	 @ResponseBody         //根据日期进行删除
	 @RequestMapping(value = "/deleteStageByDate.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public Map<String,String> deleteStageByDate(@RequestParam(value = "stage_time")String stage_time)  {
		 int res = showService.deleteByDate(stage_time);
		 //Map<String,String> map = showService.insertStage(stagelist);
		 //System.out.println(stagelist.size());
		 //List<Stage> stages = stageService.selectStageListByDate(stage_time);
		 Map<String,String> map  = new HashMap<String,String>();
		 if(res>0) {
	    	 map.put("res", "删除成功！");
	     }else {
	    	 map.put("res", "删除失败！");
	     }
		 return map;
	 }
	 
	 @ResponseBody         //删除舞台数据
	 @RequestMapping(value = "/deleteStage.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public Map<String,String> deleteStage(@RequestParam(value = "stage_time")String stage_time,@RequestParam(value = "stage_name")String stage_name)  {
		 int res = showService.deleteStage(stage_time, stage_name);
		 //Map<String,String> map = showService.insertStage(stagelist);
		 //System.out.println(stagelist.size());
		 //List<Stage> stages = stageService.selectStageListByDate(stage_time);
		 Map<String,String> map  = new HashMap<String,String>();
		 if(res>0) {
	    	 map.put("res", "删除成功！");
	     }else {
	    	 map.put("res", "删除失败！");
	     }
		 return map;
	 }
	 @ResponseBody         //删除演出数据ById
	 @RequestMapping(value = "/deleteById.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public Map<String,String> deleteById(@RequestParam(value = "id")int id)  {
		 int res =showService.deleteByid(id);
		 Map<String,String> map  = new HashMap<String,String>();
		 if(res>0) {
	    	 map.put("res", "删除成功！");
	     }else {
	    	 map.put("res", "删除失败！");
	     }
		 return map;
	 }
	 
	 @ResponseBody         //更改舞台数据
	 @RequestMapping(value = "/updateStage.do", method = {RequestMethod.POST})
	 public Map<String,String> updateStage(@RequestBody(required = true)List<Show> stagelist)  {		
		Map<String,String> map  = showService.updateStage(stagelist);
	     return map;
	 }
	 
	 //插入一条演出信息
	 @ResponseBody         
	 @RequestMapping(value = "/addPerformance.do", method = {RequestMethod.POST})
	 public Map<String,String> addPerformance(Show show)  {
		 Map<String,String> map = showService.addPerformance(show);		 
	     return map;
	 }
	 
	//修改一条演出信息
	 @ResponseBody         
	 @RequestMapping(value = "/updatePerformance.do", method = {RequestMethod.POST})
	 public Map<String,String> updatePerformance(Show show)  {
		 Map<String,String> map =new HashMap<String,String>();
		 int res= showService.updatePerformanById(show);
		 if(res>0) {
			 map.put("res", "修改成功！"); 
		 }else {
			 map.put("res", "修改失败！"); 
		 }
	 	 
	     return map;
	 }
	
	 //查询所有的演出信息按照点击量降序排序    pc端统计接口
	 @ResponseBody         
	 @RequestMapping(value = "/selShowsList.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public List<Show> selShowsList()  {
		 Map<String,String> map = new HashMap<String,String>();
		 List<Show> shows = showService.selectShowsList();
	     return shows;
	 }
	
	 
	 //查询所有舞台信息  小程序端
	 @ResponseBody            
	 @RequestMapping(value = "/wxselectStages.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public List<String> selectStages()  {
		 Map<String,List> map = new HashMap<String,List>();
		 List<String> list = showService.selectStagesXiao();
		 if(list == null) {
			 return null;
		 }else {
			 return list;
		 }		 	 			 
	 }
	 
	 //查询所有日期信息  小程序端
	 @ResponseBody            
	 @RequestMapping(value = "/wxselectDates.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public List<String> wxselectDates()  {
		 Map<String,List> map = new HashMap<String,List>();
		 List<String> list = showService.selectTimesXiao();
		 if(list == null) {
			 return null;
		 }else {
			 return list;
		 }		 	 			 
	 }
	 
	 //按照日期和舞台名称进行查找演出信息,按照演出时间升序 小程序端
	 @ResponseBody            
	 @RequestMapping(value = "/wxselectByDate.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public List<Show> wxselectByDate(@RequestParam(value = "stage_time")String stage_time,@RequestParam(value = "stage_name")String stage_name)  {
		// Map<String,List> map = new HashMap<String,List>();
			 List<Show> list = showService.selectShowByDateAndTime(stage_name, stage_time); 
			 if(list == null) {
				 return null;
			 }else {
				 return list;
			 }		
		 
		  	 			 
	 }
	 
	 //按照日期和舞台名称进行查找演出信息,按照舞台升序 小程序端
	 @ResponseBody            
	 @RequestMapping(value = "/wxselectByStage.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public List<Show> wxselectByStage(@RequestParam(value = "stage_time")String stage_time,@RequestParam(value = "stage_name")String stage_name)  {
		// Map<String,List> map = new HashMap<String,List>();
		 List<Show> list = showService.selectStagesByStageOrder(stage_name, stage_time);
		 if(list == null) {
			 return null;
		 }else {
			 return list;
		 }		 	 			 
	 }
	 
	//给演出点赞红心  小程序端接口
	 @ResponseBody         
	 @RequestMapping(value = "/updatePerformanceCount.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public Map<String,String> updatePerformanCount(@Param("means")String means,@Param("id")int id)  {
		 Map<String,String> map = new HashMap<String,String>();
		 int res= showService.updatePerformanCount(means, id);
		 if(res>0) {
			 map.put("res", "修改成功！"); 
		 }else {
			 map.put("res", "修改失败！"); 
		 }	 	 
	     return map;
	 }
	 
	 //小程序端 得到id进去之后的所有演员演出信息
	 @ResponseBody         
	 @RequestMapping(value = "/selConcrete.do", method = {RequestMethod.POST, RequestMethod.GET})
	 public Show selConcrete(@Param("id")int id)  {
		 Show show = showService.selectOnePerformance(id);
	     return show;
	 }
	 
	 
	 
			
}
