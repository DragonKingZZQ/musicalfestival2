package com.music.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.music.entity.Show;


public interface ShowService{

	List<String> selectDateList();

	Map<String, String> addStage(List<Show> shows);

	List<Show> selectStageListByDate(String stage_time);

	int deleteStage(String stage_time, String stage_name);

	Map<String, String> updateStage(List<Show> stagelist);

	Map<String, String> addPerformance(Show show);

	List<Show> selectShowsList();
	
	// 更改演出信息 -->
	int updatePerformanById(@Param("Show")Show show);

	
	//按照日期进行删除
	int deleteByDate(@Param("stage_time")String stage_time);
	//根据演出id删除演出
	int  deleteByid(@Param("id")int id);
	//根据艺人id删除演出
	int deleteByactorid(@Param("id")int id);
	
	//按照日期和舞台名称进行查找演出信息,按照演出时间升序 小程序端
	List<Show> selectShowByDateAndTime(@Param("stage_name")String stage_name,@Param("stage_time")String stage_time);
			
	//按照日期和舞台名称进行查找演出信息,按照舞台升序 小程序端
	List<Show> selectStagesByStageOrder(@Param("stage_name")String stage_name,@Param("stage_time")String stage_time);
				
	
	//请求所有不同日期  小程序端
	List<String> selectTimesXiao();
			
	//请求所有舞台  小程序端
	List<String> selectStagesXiao();
	
	//给演出点赞红心
	int updatePerformanCount(@Param("means")String means,@Param("id")int id);
	
	//小程序端点击id查询所有数据
	Show selectOnePerformance(@Param("id")int id);
	
	//小程序端做定时任务
	Show selectTimeById(@Param("id")int id);
	
	//根据演出信息得到演出id
	int selectIdByShow(@Param("show")Show show);
}


