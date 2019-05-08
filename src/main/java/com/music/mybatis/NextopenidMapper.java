package com.music.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.music.entity.Nextopenid;

public interface NextopenidMapper {
	//查询全部nextopendi
	List<Nextopenid> findAllnextopenid();
//修改nextopendi
	int updateNextopenidById(@Param("id")String id);
//添加nextopenid
	int insertNextopenid(@Param("Nextopenid")Nextopenid nextopenid);
}
