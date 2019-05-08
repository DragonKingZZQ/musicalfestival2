package com.music.daoimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.music.dao.ActorDao;

import com.music.entity.Actor;

import com.music.mybatis.ActorMapper;

@Repository
public class ActorDaoImpl implements ActorDao{
	
	@Resource
	private ActorMapper actorMapper;

	public int insertActor(Actor actor) {
		// TODO Auto-generated method stub
		return actorMapper.insertActor(actor);
	}

	public int modifiedActor(Actor actor) {
		// TODO Auto-generated method stub
		return actorMapper.modifiedActor(actor);
	}

	public List<Actor> selectActorList() {
		// TODO Auto-generated method stub
		return actorMapper.selectActorList();
	}

	public int deleteActor(int id) {
		// TODO Auto-generated method stub
		return actorMapper.deleteActor(id);
	}

	public Actor selectActorById(int id) {
		// TODO Auto-generated method stub
		return actorMapper.selectActorById(id);
	}

	
	
	
	
	
	
}
