package com.music.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.dao.CollectDao;
import com.music.dao.MidiDao;
import com.music.dao.WXvoUserDao;
import com.music.dao.XWUserDao;
import com.music.entity.Collect;
import com.music.entity.Midi;
import com.music.entity.WXUser;
import com.music.entity.WXVo;
import com.music.service.WXService;


@Service
public class WXServiceImpl implements WXService{
	//依赖持久层：注入
	
	@Autowired
	private WXvoUserDao wxvouserDao;
	
	@Autowired
	private CollectDao collectDao;
	
	@Autowired
	private XWUserDao xwuserDao;
	
	@Autowired
	private MidiDao midiDao;

	public int insertCollect(String openid, int perid) {
		// TODO Auto-generated method stub
		return collectDao.insertCollect(openid, perid);
	}

	public int deleteCollect(String openid, int perid) {
		// TODO Auto-generated method stub
		return collectDao.deleteCollect(openid, perid);
	}

	public Collect selectCollect(String openid, int perid) {
		// TODO Auto-generated method stub
		return collectDao.selectCollect(openid, perid);
	}

	public List<WXVo> wxselectBystage(String openid, String stage_time) {
		// TODO Auto-generated method stub
		return wxvouserDao.wxselectBystage(openid, stage_time);
	}

	public List<WXVo> wxselectBytime(String openid, String stage_time, String stage_name) {
		// TODO Auto-generated method stub
		return wxvouserDao.wxselectBytime(openid, stage_time, stage_name);
	}

	@Override
	public List<WXVo> wxselectNextShow(String openid, String stage_time) {
		// TODO Auto-generated method stub
		return wxvouserDao.wxselectNextShow(openid, stage_time);
	}

	@Override
	public String wxselectMinTime(String now_time) {
		// TODO Auto-generated method stub
		return wxvouserDao.wxselectMinTime(now_time);
	}

	@Override
	public List<WXVo> wxselectByActor(String openid, int actor_id) {
		// TODO Auto-generated method stub
		return wxvouserDao.wxselectByActor(openid, actor_id);
	}

	@Override
	public List<WXVo> wxselectByOpenIdAndDate(String openid, String stage_time) {
		// TODO Auto-generated method stub
		return wxvouserDao.wxselectByOpenIdAndDate(openid, stage_time);
	}
	//小程序相关
	@Override
	public int insertWXUserTwoValue(WXUser wx_user) {
		// TODO Auto-generated method stub
		return xwuserDao.insertWXUserTwoValue(wx_user);
	}
	
	

	@Override
	public int insertWXUser(String openid) {
		// TODO Auto-generated method stub
		return xwuserDao.insertWXUser(openid);
	}

	@Override
	public WXUser selectOpenid(String openid) {
		// TODO Auto-generated method stub
		return xwuserDao.selectOpenid(openid);
	}
	
	
	//谜底公众号相关
	
	//迷笛相关
		@Override
		public int insertMIDI(String openid) {
			// TODO Auto-generated method stub
			return midiDao.insertMIDI(openid);
		}

		@Override
		public Midi selectMidiByopenid(String openid) {
			// TODO Auto-generated method stub
			return midiDao.selectMidiByopenid(openid);
		}

		@Override
		public int insertMIDITwoValue(Midi midi) {
			// TODO Auto-generated method stub
			return midiDao.insertMIDITwoValue(midi);
		}

		@Override
		public int updateMIDITwoValue(Midi midi) {
			// TODO Auto-generated method stub
			return midiDao.updateMIDITwoValue(midi);
		}

		@Override
		public Midi selectMidiByunionid(String unionid) {
			// TODO Auto-generated method stub
			return midiDao.selectMidiByunionid(unionid);
		}

		@Override
		public int updateByOpenid(WXUser wx_user) {
			// TODO Auto-generated method stub
			return xwuserDao.updateByOpenid(wx_user);
		}

		@Override
		public int updateCollectSend(String openid, String perid) {
			// TODO Auto-generated method stub
			return collectDao.updateCollectSend(openid, perid);
		}

		@Override
		public List<Collect> selecCollectByperid(String perid) {
			// TODO Auto-generated method stub
			return collectDao.selecCollectByperid(perid);
		}

		@Override
		public List<Midi> findAllMidis() {
			// TODO Auto-generated method stub
			return midiDao.findAllMidis();
		}

		@Override
		public List<Midi> findAllNothaveUnionid() {
			// TODO Auto-generated method stub
			return midiDao.findAllNothaveUnionid();
		}
	
	
}
