package com.music.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.music.entity.HandBook;

public interface HandBookMapper {    
	int deleteById(@Param("id")int id);
	
	List<HandBook> selectAll();
	
	int insertHandBook(@Param("handbook_url")String handbook_url);
	
	
}
