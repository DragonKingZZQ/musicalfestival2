package com.music.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.music.entity.Link;


public interface LinkDao {
			
	int updateByLinkType(@Param("link_type")int link_type,@Param("link_url")String link_url);
	
	Link selectByLinktype(@Param("link_type")int link_type);
	
	int insertByLinktype(@Param("link_type")int link_type,@Param("link_url")String link_url);
	
	List<Link> selectLinkList();
	
	String selectByPDFOrHTML();
}
