package com.music.daoimpl;


import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Repository;

import com.music.dao.ShowDao;

import com.music.entity.Show;
import com.music.mybatis.ShowMapper;


@Repository
public class ShowDaoImpl implements ShowDao{
	
	@Resource
	private ShowMapper showMapper;

	public int insertStage(List<Show> stages) {
		// TODO Auto-generated method stub
		return showMapper.insertStage(stages);
	}

	public int deleteStage(String stage_name, String stage_time) {
		// TODO Auto-generated method stub
		return showMapper.deleteStage(stage_name, stage_time);
	}

	public int deleteByDate(String stage_time) {
		// TODO Auto-generated method stub
		return showMapper.deleteByDate(stage_time);
	}

	public List<String> selectDateList() {
		// TODO Auto-generated method stub
		return showMapper.selectDateList();
	}

	public List<Show> selectStageList(String stage_time) {
		// TODO Auto-generated method stub
		return showMapper.selectStageList(stage_time);
	}

	public int insertStageOne(Show stage) {
		// TODO Auto-generated method stub
		return showMapper.insertStageOne(stage);
	}

	public int updateStageById(Show stage) {
		// TODO Auto-generated method stub
		return showMapper.updateStageById(stage);
	}

	

	public List<Show> selectShowssList() {
		// TODO Auto-generated method stub
		return showMapper.selectShowssList();
	}

	public int insertShow(Show stage) {
		// TODO Auto-generated method stub
		return showMapper.insertShow(stage);
	}


	public int updatePerformanById(Show show) {
		// TODO Auto-generated method stub
		return showMapper.updatePerformanById(show);
	} 

	public int updatePerformanCount(String means, int id) {
		// TODO Auto-generated method stub
		return showMapper.updatePerformanCount(means, id);
	}

	public Show selectOnePerformance(int id) {
		// TODO Auto-generated method stub
		return showMapper.selectOnePerformance(id);
	}

	public Show selectTimeById(int id) {
		// TODO Auto-generated method stub
		return showMapper.selectTimeById(id);
	}

	public List<String> selectTimesXiao() {
		// TODO Auto-generated method stub
		return showMapper.selectTimesXiao();
	}

	public List<String> selectStagesXiao() {
		// TODO Auto-generated method stub
		return showMapper.selectStagesXiao();
	}

	public List<Show> selectShowByDateAndTime(String stage_name, String stage_time) {
		// TODO Auto-generated method stub
		return showMapper.selectShowByDateAndTime(stage_name, stage_time);
	}

	public List<Show> selectStagesByStageOrder(String stage_name, String stage_time) {
		// TODO Auto-generated method stub
		return showMapper.selectStagesByStageOrder(stage_name, stage_time);
	}

	@Override
	public int selectIdByShow(Show show) {
		// TODO Auto-generated method stub
		return showMapper.selectIdByShow(show);
	}

	@Override
	public int deleteByid(int id) {
		// TODO Auto-generated method stub
		return showMapper.deleteByid(id);
	}

	@Override
	public int deleteByactorid(int id) {
		// TODO Auto-generated method stub
		return showMapper.deleteByactorid(id);
	}

	

	

	
	
}
