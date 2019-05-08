package com.music.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.music.entity.Midi;


public interface MidiDao {
	int insertMIDI(@Param("openid")String openid);
	
	Midi selectMidiByopenid(@Param("openid")String openid);
	
	int insertMIDITwoValue(@Param("midi")Midi midi);
	
	int updateMIDITwoValue(@Param("midi")Midi midi);
	
	Midi selectMidiByunionid(@Param("unionid")String unionid);
	
	List<Midi> findAllMidis();
	
	List<Midi> findAllNothaveUnionid();
	
}
