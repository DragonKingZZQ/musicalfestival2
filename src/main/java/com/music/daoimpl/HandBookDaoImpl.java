package com.music.daoimpl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.music.dao.HandBookDao;
import com.music.entity.HandBook;
import com.music.mybatis.HandBookMapper;


@Repository
public class HandBookDaoImpl implements HandBookDao{
	
	@Resource
	private HandBookMapper handBookMapper;

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return handBookMapper.deleteById(id);
	}

	@Override
	public List<HandBook> selectAll() {
		// TODO Auto-generated method stub
		return handBookMapper.selectAll();
	}

	@Override
	public int insertHandBook(String handbook_url) {
		// TODO Auto-generated method stub
		return handBookMapper.insertHandBook(handbook_url);
	}

	
}
