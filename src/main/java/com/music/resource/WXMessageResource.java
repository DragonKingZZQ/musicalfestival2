package com.music.resource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.music.config.WeChatAccountConfig;
import com.music.dao.WXvoUserDao;
import com.music.entity.MapWxVo;
import com.music.entity.Midi;
import com.music.entity.Nextopenid;
import com.music.entity.Show;
import com.music.entity.WXUser;
import com.music.entity.WXVo;
import com.music.service.ShowService;
import com.music.service.WXMessageService;
import com.music.service.WXService;
import com.music.util.MidiGZH;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

@Controller
@RequestMapping(value = { "/WXMessage" })
public class WXMessageResource {
	// 依赖服务层，自动注入
	@Autowired
	private WXService xwService;

	@Autowired
	private ShowService showService;

	@Autowired
	private WXMessageService wxMessageService;

	@Autowired
	private WeChatAccountConfig weChatAccountConfig;

	// 用户授权登录
	@RequestMapping("/authorize.do")
	@ResponseBody
	public Map<String, String> authorize(
			@RequestParam("js_code") String js_code,
			@RequestParam("iv") String iv,
			@RequestParam("encryptedData") String encryptedData) {
		Map<String, String> map = new HashMap<String, String>();
		URL url1 = this.getClass().getClassLoader()
				.getResource("wxContext.properties");
		Properties pro = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream(url1.getPath());
			pro.load(in);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String appid = pro.get("AppId").toString();// 小程序的appid
		String secret = pro.get("AppSecret").toString(); // 小程序的secret
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid="
				+ appid + "&secret=" + secret + "&js_code=" + js_code
				+ "&grant_type=authorization_code";
		System.out.println("dhfsjhfjs");
		String result = getUrl(url);
		JSONObject resultjson = JSON.parseObject(result);
		// 获取session_key
		String session_key = resultjson.getString("session_key");
		// 解密用户信息
		MidiGZH midiGZH = new MidiGZH();
		JSONObject userinfoJsonObject = midiGZH.getUserInfo(encryptedData,
				session_key, iv);
		System.out.println(userinfoJsonObject);
		System.out.println(userinfoJsonObject.getString("openId"));
		// 获取用户unionid和openid
		String openid = resultjson.getString("openid");
		String unionid = userinfoJsonObject.getString("unionId");
		// 插入数据库
		// 查询是否以及存在该用户
		WXUser wxUser = xwService.selectOpenid(openid);
		// 存在更新
		if (wxUser != null) {
			wxUser.setUnionid(unionid);
			xwService.updateByOpenid(wxUser);
		} else {
			// 不存在查询插入
			wxUser = new WXUser();
			wxUser.setOpenid(openid);
			wxUser.setUnionid(unionid);
			xwService.insertWXUserTwoValue(wxUser);
		}
		map.put("openid", resultjson.getString("openid"));
		System.out.println(resultjson + "result");
		return map;
	}

	// 用户关注演出
	@RequestMapping("/focus.do")
	@ResponseBody
	public Map<String, String> focus(@RequestParam("openid") String openid,
			@RequestParam("id") int id) {
		Map<String, String> map = new HashMap<String, String>();
		// 1.查询用户是否关注过公众号
		// 查询unionid
		WXUser wx = xwService.selectOpenid(openid);
		String unionid = wx.getUnionid();
		System.out.println(unionid);
		Midi midi = xwService.selectMidiByunionid(unionid);
		if (midi != null) {
			// 发送已设置提醒模板消息
			Show show=showService.selectOnePerformance(id);
			// 获取演出开始时间时间戳
			long time = sege(show);
			// 获取演出结束时间时间戳
			long timeend = segeend(show);
			// 与当前时间比较
			Date now = new Date();
			if(now.getTime() >=time - 10 * 60 * 1000&&now.getTime()<timeend){
				map.put("code", "1");
				map.put("msg", "演出即将开始！");
			}else if (now.getTime()>=timeend) {
				map.put("code", "2");
				map.put("msg", "演出已结束！");
			}else {
//				sendMessage(midi.getOpenid());
				map.put("code", "0");
				map.put("msg", "已设置提醒！");
			}

		} else {
			// 拉取迷笛用户列表
			MidiGZH midiGZH = new MidiGZH();
			String appid = weChatAccountConfig.getMpAppId();
			String secret = weChatAccountConfig.getMpAppSecret();
			// 获取token
			String access_token = midiGZH.gettoken(appid, secret);
			System.out.println("access_token="+access_token);
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
			if(midiusers!=null)
			for (int i = 0; i < midiusers.size(); i++) {
				// 查询公众用户的unuonid
				String unionid2 = midiGZH.getUserUnionid(midiusers.get(i)
						.getOpenid(), access_token);
				midiusers.get(i).setUnionid(unionid2);
				// 插入迷笛公众号用户
				xwService.insertMIDITwoValue(midiusers.get(i));
			}
			// 再次查找迷笛用户openid
			wx = xwService.selectOpenid(openid);
			unionid = wx.getUnionid();
			midi = xwService.selectMidiByunionid(unionid);
			System.out.println(midi);
			// 如果有则发送无则提示
			if (midi != null) {
				// 设置已设置提醒发送模板消息
//				sendMessage(midi.getOpenid());
				// 2.给用户添加关注(由/collect.do完成)
				Show show=showService.selectOnePerformance(id);
				// 获取演出开始时间时间戳
				long time = sege(show);
				// 获取演出结束时间时间戳
				long timeend = segeend(show);
				// 与当前时间比较
				Date now = new Date();
				if(now.getTime() >=time - 10 * 60 * 1000&&now.getTime()<timeend){
					map.put("code", "1");
					map.put("msg", "演出即将开始！");
				}else if (now.getTime()>=timeend) {
					map.put("code", "2");
					map.put("msg", "演出已结束！");
				}else {
//					sendMessage(midi.getOpenid());
					map.put("code", "0");
					map.put("msg", "已设置提醒！");
				}
			} else {
				// 提示关注需要关注服务号
				map.put("code", "-1");
				map.put("msg", "请先关注测试服务号");
			}
		}

		return map;
	}

	private String getUrl(String url) {
		// 实例化httpclient
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 实例化get方法
		HttpGet httpget = new HttpGet(url);
		HttpPost req = new HttpPost(url);
		// 请求结果
		CloseableHttpResponse response = null;
		String content = "";

		try {
			// 执行get方法
			response = httpclient.execute(httpget);
			if (response.getStatusLine().getStatusCode() == 200) {
				content = EntityUtils.toString(response.getEntity(), "utf-8");
				System.out.println(content + "content");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

	// 发送模板

	public void sendMessage(@RequestParam("openid") String userOpenId) {
		wxMessageService.sendMessage(userOpenId);
	}
	//计算开始时间
	public long sege(Show show) {
		// 插入数据的同时
		String show_date = show.getStage_time();
		String show_time = show.getShow_time();
		String[] show_times = show_time.split("-");
		String time = show_date + show_times[0];
		// System.out.println(time);
		DateFormat df = new SimpleDateFormat("yyyy-MM-ddhh:mm:ss");
		Date date = null;
		try {
			date = df.parse(time);
			return date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 年月日演出时间
		return 0;
	}
	//计算结束时间
	public long segeend(Show show) {
		// 插入数据的同时
		String show_date = show.getStage_time();
		String show_time = show.getShow_time();
		String[] show_times = show_time.split("-");
		String time = show_date + show_times[1];
		// System.out.println(time);
		DateFormat df = new SimpleDateFormat("yyyy-MM-ddhh:mm:ss");
		Date date = null;
		try {
			date = df.parse(time);
			return date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 年月日演出时间
		return 0;
	}
}
