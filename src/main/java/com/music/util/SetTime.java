package com.music.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.util.json.WxUserBlacklistGetResultGsonAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.music.config.WeChatAccountConfig;
import com.music.entity.Collect;
import com.music.entity.Midi;
import com.music.entity.Nextopenid;
import com.music.entity.Show;
import com.music.entity.WXUser;
import com.music.service.ShowService;
import com.music.service.WXMessageService;
import com.music.service.WXService;

@EnableScheduling
@Controller
public class SetTime {
	// 依赖服务层，自动注入
	@Autowired
	private ShowService showService;
	@Autowired
	private WXService wxService;
	@Autowired
	private WXMessageService wxMessageService;
	@Autowired
	private WeChatAccountConfig weChatAccountConfig;

	// 发送定时消息
	@Scheduled(cron = "* * * * * ?")
	public void sendmsg() {
		System.out.println("监听用户模板消息");
		// collect增加是否以发送的字段 0 未发送 1已发送
		List<Show> shows = showService.selectShowsList();
		System.out.println("shows长度="+shows.size());
		if (shows.size() > 0) {
			// 循环获取每一条演出
			for (int i = 0; i < shows.size(); i++) {
				// 获取演出开始时间时间戳
				long time = sege(shows.get(i));
				// 与当前时间比较
				Date now = new Date();
				// 如果当前时间大于演出开始时间
				if (now.getTime() >= time - 10 * 60 * 1000) {
					// 根据演出id查询关注了该演出的用户列表
					int perid = shows.get(i).getId();
					List<Collect> collects = wxService
							.selecCollectByperid(String.valueOf(perid));
					// 循环判断是否已经发送信息
					for (int j = 0; j < collects.size(); j++) {
						// 如果字段为0
						if (collects.get(j).getSend() == 0) {
							System.out.println("collectsopenid====="+collects.get(j).getOpenid());
							//获取用户服务号的openid
							WXUser wxUser=wxService.selectOpenid(collects.get(j).getOpenid());
							String unionid=wxUser.getUnionid();
							Midi midi=wxService.selectMidiByunionid(unionid);
							
							
							if (midi!=null) {
								System.out.println("midiopenid====="+midi.getOpenid());
							
							// 获取该演出详细信息
							// 发送模板消息
							List<WxMpTemplateData> data = Arrays
									.asList(new WxMpTemplateData("first","演出还有10分钟开始"),
											new WxMpTemplateData(
													"keyword1",
													shows.get(i)
															.getStage_time()
															+ " "
															+ shows.get(i)
																	.getShow_time()),
											new WxMpTemplateData("keyword2",
													shows.get(i)
															.getStage_name()),
											new WxMpTemplateData("keyword3","太湖迷笛营"),
											new WxMpTemplateData("keyword4",
													""), new WxMpTemplateData(
													"remark", ""));
							wxMessageService.sendMessage(midi.getOpenid(), data);
							// 并将字段置为1
							collects.get(j).setSend(1);
							wxService.updateCollectSend(collects.get(j)
									.getOpenid(), String.valueOf(collects
									.get(j).getPerid()));
							System.out.println("发送至------"+collects.get(j)
									.getOpenid());
							}else {
								collects.get(j).setSend(1);
								wxService.updateCollectSend(collects.get(j)
										.getOpenid(), String.valueOf(collects
										.get(j).getPerid()));
								System.out.println("未关注迷笛");
							}
						} else {
							// 否则不操作

						}

					}

				}
			}
		}
	}

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


}
