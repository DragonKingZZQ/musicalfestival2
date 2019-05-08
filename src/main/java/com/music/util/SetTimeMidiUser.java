package com.music.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.music.config.WeChatAccountConfig;
import com.music.entity.Midi;
import com.music.entity.Nextopenid;
import com.music.service.ShowService;
import com.music.service.WXMessageService;
import com.music.service.WXService;

@EnableScheduling
@Controller
public class SetTimeMidiUser {
	// 依赖服务层，自动注入
	@Autowired
	private ShowService showService;
	@Autowired
	private WXService wxService;
	@Autowired
	private WXMessageService wxMessageService;
	@Autowired
	private WeChatAccountConfig weChatAccountConfig;
	@Autowired
	private WXService xwService;

	// 获取迷笛用户信息
	@Scheduled(cron = "0 0 0 * * ?")
	public void getuserinfo() {
		System.out.println("更新迷笛用户列表");
		// 拉取迷笛用户列表
		MidiGZH midiGZH = new MidiGZH();
		String appid = weChatAccountConfig.getMpAppId();
		String secret = weChatAccountConfig.getMpAppSecret();
		// 获取token
		String access_token = midiGZH.gettoken(appid, secret);
//		System.out.println("access_token=" + access_token);
		// 获取用户列表
		// 获取上次一的next_openid
		List<Nextopenid> nextopenids = wxMessageService.findAllnextopenid();
		Map<String, Object> maps = new HashMap<String, Object>();
		// 无则赋值null
		if (nextopenids.size() <= 0) {
			maps = midiGZH.getlist(access_token, null);
		} else {
			maps = midiGZH.getlist(access_token, nextopenids.get(0)
					.getNext_openid());
		}
		String next_openid = (String) maps.get("next_openid");
		// 将新next_openid存入数据库
		if (nextopenids.size() <= 0) {
			Nextopenid nextopenid = new Nextopenid();
			nextopenid.setId("1");
			nextopenid.setNext_openid(next_openid);
			wxMessageService.insertNextopenid(nextopenid);
		} else {
			wxMessageService.updateNextopenidById(next_openid);
		}
		// 将接列表存入数据库
		List<Midi> midiusers = (List<Midi>) maps.get("list");
		System.out.println("新用户=" + midiusers.size());
		for (int i = 0; i < midiusers.size(); i++) {
			System.out.println("正在插入新用户" + i);
			// 查询公众用户的unuonid
			String unionid2 = midiGZH.getUserUnionid(midiusers.get(i)
					.getOpenid(), access_token);
			midiusers.get(i).setUnionid(unionid2);
			// 插入迷笛公众号用户
			xwService.insertMIDITwoValue(midiusers.get(i));
		}
	}

	// 校验迷笛用户（未收录的用户）
	@Scheduled(cron = "0 0 1 * * ?")
	public void checkuserinfo() {
		System.out.println("开始校验未收录用户");
		// 拉取迷笛用户列表
		MidiGZH midiGZH = new MidiGZH();
		String appid = weChatAccountConfig.getMpAppId();
		String secret = weChatAccountConfig.getMpAppSecret();
		// 获取token
		String access_token = midiGZH.gettoken(appid, secret);
//		System.out.println("access_token=" + access_token);
		// 获取用户列表
		Map<String, Object> maps = new HashMap<String, Object>();
		maps = midiGZH.getlist(access_token, null);
		// 拿到迷笛用户列表
		List<Midi> netmidiusers = (List<Midi>) maps.get("list");
		// 拿到数据库迷笛用户列表
		List<Midi> localmidiusers = wxService.findAllMidis();
		// 查出未收录的数据
		List<Midi> diff = getDiffrentThree(netmidiusers, localmidiusers);
		System.out.println("未收录用户数量="+diff.size());
		// 为未收录的数据添加unionid
		for (Midi midi : diff) {
			// 查询公众用户的unuonid
			String unionid = midiGZH.getUserUnionid(midi.getOpenid(),
					access_token);
			midi.setUnionid(unionid);
			// 插入未收录数据
			xwService.insertMIDITwoValue(midi);
		}
	}

	// 校验已收录用户unionid
	@Scheduled(cron = "0 0 2 * * ?")
	public void checkuserunionid() {
		System.out.println("开始校验校验已收录用户unionid");
		// 拉取迷笛用户列表
		MidiGZH midiGZH = new MidiGZH();
		String appid = weChatAccountConfig.getMpAppId();
		String secret = weChatAccountConfig.getMpAppSecret();
		// 拿到数据库迷笛用户列表中无unionid用户重新获取并赋值
		List<Midi> localmidiusers = wxService.findAllNothaveUnionid();
		System.out.println("数据缺失用户数量=" + localmidiusers.size());
		// 获取token
		String access_token = midiGZH.gettoken(appid, secret);
//		System.out.println("access_token=" + access_token);
		// 每次最多更新2000条
		int length = 0;
		if (localmidiusers.size() >= 2000) {
			length = 2000;
		} else {
			length = localmidiusers.size();
		}
		// 获取unionid
		for (int i = 0; i < length; i++) {
			System.out.println("正在更新迷笛用户" + i);
			// 查询公众用户的unuonid
			String unionid = midiGZH.getUserUnionid(localmidiusers.get(i)
					.getOpenid(), access_token);
			localmidiusers.get(i).setUnionid(unionid);
			xwService.updateMIDITwoValue(localmidiusers.get(i));
		}
	}

	// 方法3，用Map存放List1和List2的元素作为key，value为其在List1和List2中出现的次数
	// 出现次数为1的即为不同元素，查找次数为list1.size() + list2.size()，较方法1和2，是极大简化
	private List<Midi> getDiffrentThree(List<Midi> list1, List<Midi> list2) {
		List<Midi> diff = new ArrayList<Midi>();
		/* long start = System.currentTimeMillis(); */
		Map<String, Integer> map = new HashMap<String, Integer>(list1.size()
				+ list2.size());
		// 将List1元素放入Map，计数1
		for (Midi midi : list1) {
			map.put(midi.getOpenid(), 1);
		}
		// 遍历List2，在Map中查找List2的元素，找到则计数+1；未找到则放入map，计数1
		for (Midi midi : list2) {
			Integer count = map.get(midi.getOpenid());
			if (count != null) {
				map.put(midi.getOpenid(), ++count);// 此处可优化，减少put次数，即为方法4
				continue;
			}
			map.put(midi.getOpenid(), 1);
		}
		Midi midi2 = new Midi();
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 1) {
				String openid = entry.getKey();
				midi2.setOpenid(openid);
				diff.add(midi2);
				midi2 = new Midi();
			}
		}
		/*
		 * System.out.println("方法3 耗时：" + (System.currentTimeMillis() - start) +
		 * " 毫秒");
		 */
		return diff;
	}

}
