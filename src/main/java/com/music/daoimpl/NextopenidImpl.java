package com.music.daoimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.music.dao.NextopenidDao;
import com.music.entity.Nextopenid;
import com.music.mybatis.NextopenidMapper;

@Repository
public class NextopenidImpl implements NextopenidDao {
	@Resource
	private NextopenidMapper nextMapper;
	
	@Override
	public List<Nextopenid> findAllnextopenid() {
		// TODO Auto-generated method stub
		return nextMapper.findAllnextopenid();
	}

	@Override
	public int updateNextopenidById(String id) {
		// TODO Auto-generated method stub
		return nextMapper.updateNextopenidById(id);
	}

	@Override
	public int insertNextopenid(Nextopenid nextopenid) {
		// TODO Auto-generated method stub
		return nextMapper.insertNextopenid(nextopenid);
	}

	

}
