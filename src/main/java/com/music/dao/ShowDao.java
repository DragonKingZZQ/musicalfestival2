package com.music.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.music.entity.Show;


public interface ShowDao {

	// 批量插入舞台数据 -->
		int insertStage(@Param("stages")List<Show> stages);
		
		//删除按钮删除舞台内所有信息
		int deleteStage(@Param("stage_name")String stage_name,@Param("stage_time")String stage_time);
		//根据演出id删除演出
		int  deleteByid(@Param("id")int id);
		//根据艺人id删除演出
		int deleteByactorid(@Param("id")int id);
		//按照日期进行删除
		int deleteByDate(@Param("stage_time")String stage_time);
		
		//查询所有不同日期
		List<String> selectDateList();
		
		// 根据日期查询所有舞台
		List<Show> selectStageList(@Param("stage_time")String stage_time);
		
		//更改中 如果无id插入一条舞台信息
		int insertStageOne(@Param("Show")Show show);
		
		//更改中 如果有id修改一条舞台信息
		int updateStageById(@Param("Show")Show show);
		
		// 插入一条演出信息
		int insertShow(@Param("Show")Show stage);
		
		//查询所有的演出信息
		List<Show> selectShowssList();
		
		// 更改演出信息 -->
		int updatePerformanById(@Param("Show")Show show);

		
		
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
