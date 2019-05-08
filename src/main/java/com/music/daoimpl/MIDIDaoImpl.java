package com.music.daoimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.music.dao.MidiDao;
import com.music.dao.XWUserDao;
import com.music.entity.Midi;
import com.music.entity.WXUser;
import com.music.mybatis.MidiMapper;
import com.music.mybatis.WXUserMapper;

@Repository
public class MIDIDaoImpl implements MidiDao{
	
	@Resource
	private MidiMapper midiMapper;

	@Override
	public int insertMIDI(String openid) {
		// TODO Auto-generated method stub
		return midiMapper.insertMIDI(openid);
	}

	@Override
	public Midi selectMidiByopenid(String openid) {
		// TODO Auto-generated method stub
		return midiMapper.selectMidiByopenid(openid);
	}

	@Override
	public int insertMIDITwoValue(Midi midi) {
		// TODO Auto-generated method stub
		return midiMapper.insertMIDITwoValue(midi);
	}

	@Override
	public int updateMIDITwoValue(Midi midi) {
		// TODO Auto-generated method stub
		return midiMapper.updateMIDITwoValue(midi);
	}

	@Override
	public Midi selectMidiByunionid(String unionid) {
		// TODO Auto-generated method stub
		return midiMapper.selectMidiByunionid(unionid);
	}

	@Override
	public List<Midi> findAllMidis() {
		// TODO Auto-generated method stub
		return midiMapper.findAllMidis();
	}

	@Override
	public List<Midi> findAllNothaveUnionid() {
		// TODO Auto-generated method stub
		return midiMapper.findAllNothaveUnionid();
	}

	
	
}
