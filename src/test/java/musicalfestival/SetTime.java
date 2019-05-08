package musicalfestival;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.music.entity.Collect;
import com.music.entity.Show;
import com.music.service.ShowService;
import com.music.service.WXMessageService;
import com.music.service.WXService;

@EnableScheduling
public class SetTime {
	// 依赖服务层，自动注入
	@Autowired
	private ShowService showService;
	@Autowired
	private WXService wxService;
	@Autowired
	private WXMessageService wxMessageService;
	@Scheduled(cron = "0 */2 * * * ?")
	public void sendmsg() {
		// collect增加是否以发送的字段 0 未发送 1已发送
		List<Show> shows = showService.selectShowsList();
		if (shows.size() > 0) {
			// 循环获取每一条演出
			for (int i = 0; i < shows.size(); i++) {
				// 获取演出开始时间时间戳
				long time = sege(shows.get(i));
				// 与当前时间比较
				Date now = new Date();
				// 如果当前时间大于演出开始时间
				if (now.getTime() >= time-10*60*1000) {
					// 根据演出id查询关注的用户列表
					int perid = shows.get(i).getId();
					List<Collect> collects = wxService
							.selecCollectByperid(String.valueOf(perid));
					// 循环判断是否已经发送信息
					for (int j = 0; j < collects.size(); j++) {
						// 如果字段为0
						if (collects.get(j).getSend() == 0) {
							// 获取该演出详细信息
							// 发送模板消息
							List<WxMpTemplateData> data = Arrays.asList(
									new WxMpTemplateData("first", shows.get(i).getStage_name()),
									new WxMpTemplateData("keyword1", shows.get(i).getActor().getActor_name()),
									new WxMpTemplateData("keyword2", shows.get(i).getShow_time()),
									new WxMpTemplateData("keyword3", shows.get(i).getStage_time()),
									new WxMpTemplateData("keyword4", shows.get(i).getActor().getIntroduce()),
									new WxMpTemplateData("keyword5", shows.get(i).getStage_name()),
									new WxMpTemplateData("remark", shows.get(i).getStage_name()));
							wxMessageService.sendMessage(collects.get(j).getOpenid(),data);
							// 并将字段置为1
							collects.get(j).setSend(1);
							wxService.updateCollectSend(collects.get(j)
									.getOpenid(), String.valueOf(collects
									.get(j).getPerid()));
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
