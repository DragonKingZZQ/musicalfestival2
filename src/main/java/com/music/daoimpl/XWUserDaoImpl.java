package com.music.daoimpl;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.music.dao.XWUserDao;
import com.music.entity.WXUser;
import com.music.mybatis.WXUserMapper;

@Repository
public class XWUserDaoImpl implements XWUserDao{
	
	@Resource
	private WXUserMapper wxuserMapper;

	public int insertWXUser(String openid) {
		// TODO Auto-generated method stub
		return wxuserMapper.insertWXUser(openid);
	}

	public WXUser selectOpenid(String openid) {
		// TODO Auto-generated method stub
		return wxuserMapper.selectOpenid(openid);
	}

	@Override
	public int insertWXUserTwoValue(WXUser wx_user) {
		// TODO Auto-generated method stub
		return wxuserMapper.insertWXUserTwoValue(wx_user);
	}

	@Override
	public int updateByOpenid(WXUser wx_user) {
		// TODO Auto-generated method stub
		return wxuserMapper.updateByOpenid(wx_user);
	}

	
}
