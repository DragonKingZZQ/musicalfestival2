package com.music.serviceimpl;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.music.dao.ShowDao;
import com.music.entity.Show;
import com.music.service.ShowService;


@Service
public class ShowServiceImpl implements ShowService{
	//依赖持久层：注入
	@Autowired
	private ShowDao showDao;
	
	
	public List<String> selectDateList() {
		// TODO Auto-generated method stub
		return showDao.selectDateList();
	}
	public Map<String,String> insertStage(List<Show> shows) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String,String>();
		List<Show> stageli = showDao.selectStageList(shows.get(0).getStage_time());
		//如果按照日期查找发现数据库有数据，先把对应日期下面数据删除
		if(stageli.size()>0) {   
			int delres = showDao.deleteByDate(shows.get(0).getStage_time());
			int insertres = showDao.insertStage(shows);
			if(insertres > 0) {
				map.put("res", "数据插入成功，并成功将上次设置数据清除！");
			}
		}else {   //直接进行插入
			int insertres = showDao.insertStage(shows);
			map.put("res", "数据新插入成功！");
		}
		return map;
	}
	
	public List<Show> selectStageListByDate(String stage_time) {
		// TODO Auto-generated method stub
		return showDao.selectStageList(stage_time);
	}

	/*
	public int insertDate(String stage_time) {
		// TODO Auto-generated method stub
		return stageDao.insertDate(stage_time);
	}

	public List<Stage> selectStageList() {
		// TODO Auto-generated method stub
		return stageDao.selectStageList();
	}

	
	public int modifiedStage(Stage stage) {
		// TODO Auto-generated method stub
		return stageDao.modifiedStage(stage);
	}

	public int deleteByDate(String stage_time) {
		// TODO Auto-generated method stub
		return stageDao.deleteByDate(stage_time);
	}
*/
	public Map<String, String> addStage(List<Show> shows) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		 int insertres = showDao.insertStage(shows);
		 if(insertres>0) {
			 map.put("res", "插入成功！");
		 }else {
			 map.put("res", "插入失败！");
		 }
		return map;
	}
	public int deleteStage(String stage_time, String stage_name) {
		// TODO Auto-generated method stub
		return showDao.deleteStage(stage_name, stage_time);
	}
	public Map<String,String> updateStage(List<Show> stagelist) {
		// TODO Auto-generated method stub
		int insertres = 0;
		int updateres = 0;
		Map<String,String> map = new HashMap<String,String>();
		for(Show show:stagelist) {
			if(show.getId() == -1) {   //id是-1则是新的进行插入
			  insertres = showDao.insertStageOne(show);
			  map.put("insertres", "有新数据加入操作");
			}else {
				updateres = showDao.updateStageById(show);
				map.put("updateres", "有数据更新操作");
			}
		}
		return map;
	}
	public Map<String, String> addPerformance(Show show) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		int res = showDao.insertShow(show);
		if(res>0) {
			map.put("res", "添加成功！");			
		}else {
			map.put("res", "添加失败！");
		}
		return map;
	}
	public List<Show> selectShowsList() {
		// TODO Auto-generated method stub
		return showDao.selectShowssList();
	}
	public int updatePerformanById(Show show) {
		// TODO Auto-generated method stub
		return showDao.updatePerformanById(show);
	}
	public int deleteByDate(String stage_time) {
		// TODO Auto-generated method stub
		return showDao.deleteByDate(stage_time);
	}
	public int updatePerformanCount(String means, int id) {
		// TODO Auto-generated method stub
		return showDao.updatePerformanCount(means, id);
	}
	public Show selectOnePerformance(int id) {
		// TODO Auto-generated method stub
		return showDao.selectOnePerformance(id);
	}
	public Show selectTimeById(int id) {
		// TODO Auto-generated method stub
		return showDao.selectTimeById(id);
	}
	
	
	
	
	public List<String> selectTimesXiao() {
		// TODO Auto-generated method stub
		return showDao.selectTimesXiao();
	}
	public List<String> selectStagesXiao() {
		// TODO Auto-generated method stub
		return showDao.selectStagesXiao();
	}
	public List<Show> selectShowByDateAndTime(String stage_name, String stage_time) {
		// TODO Auto-generated method stub
		return showDao.selectShowByDateAndTime(stage_name, stage_time);
	}
	public List<Show> selectStagesByStageOrder(String stage_name, String stage_time) {
		// TODO Auto-generated method stub
		return showDao.selectStagesByStageOrder(stage_name, stage_time);
	}
	@Override
	public int selectIdByShow(Show show) {
		// TODO Auto-generated method stub
		return showDao.selectIdByShow(show);
	}
	@Override
	public int deleteByid(int id) {
		// TODO Auto-generated method stub
		return showDao.deleteByid(id);
	}
	@Override
	public int deleteByactorid(int id) {
		// TODO Auto-generated method stub
		return showDao.deleteByactorid(id);
	}
	
}
