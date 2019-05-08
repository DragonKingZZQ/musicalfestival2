package com.music.daoimpl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import com.music.dao.LinkDao;
import com.music.entity.Link;
import com.music.mybatis.LinkMapper;


@Repository
public class LinkDaoImpl implements LinkDao{
	
	@Resource
	private LinkMapper linkMapper;

	

	public Link selectByLinktype(int link_type) {
		// TODO Auto-generated method stub
		return linkMapper.selectByLinktype(link_type);
	}

	

	public int insertByLinktype(int link_type, String link_url) {
		// TODO Auto-generated method stub
		return linkMapper.insertByLinktype(link_type, link_url);
	}

	public List<Link> selectList() {
		// TODO Auto-generated method stub
		return linkMapper.selectLinkList();
	}

	public int updateByLinkType(int link_type, String link_url) {
		// TODO Auto-generated method stub
		return linkMapper.updateByLinkType(link_type, link_url);
	}



	public String selectByPDFOrHTML() {
		// TODO Auto-generated method stub
		return linkMapper.selectByPDFOrHTML();
	}



	public List<Link> selectLinkList() {
		// TODO Auto-generated method stub
		return linkMapper.selectLinkList();
	}



	

	
	
}
