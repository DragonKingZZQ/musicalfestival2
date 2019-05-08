package musicalfestival;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import com.music.entity.Show;
import com.music.service.ShowService;

public class ResidTest {
	
	@Autowired
	private ShowService showService;
	@Autowired
	private StringRedisTemplate redisTolenTemplate; 
	
    @Test
    public void sege() {
    	//插入数据的同时
    	String show_date = "2019-4-22";
    	String show_time = "02:24:00 - 03:00:00";
		String[] show_times = show_time.split("-");
		String time = show_date+show_times[0];
		//System.out.println(time);
		DateFormat df = new SimpleDateFormat("yyyy-MM-ddhh:mm:ss");
		Date date = null;
		try {
			 date= df.parse(time);
			 System.out.println(date.getTime()+"时间");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //年月日演出时间
    	
    	 //int perid = showService.selectIdByShow(show);
		int perid = 68;
    	// redisTolenTemplate.opsForValue().set("perid", perid+"");
    	// redisTolenTemplate.expireAt("perid", date);  //在指定时间失效
    	// redisTolenTemplate.expire("perid", 1, TimeUnit.SECONDS);
    	// redisTolenTemplate.expi
    }
    
   
}


