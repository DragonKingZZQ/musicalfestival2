/*package musicalfestival;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

public class TimeerTest {
	@Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig accountConfig;
		@Test
	    public void orderStatus() {
	    	URL url = this.getClass().getClassLoader().getResource("wxContext.properties");
			Properties pro = new Properties();
			FileInputStream in;
			try {
				in = new FileInputStream(url.getPath());
				pro.load(in);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String appId = pro.get("AppId").toString();
			String appSecret = pro.get("AppSecret").toString();
	        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
	        //模板id
	        //templateMessage.setTemplateId(accountConfig.getTemplateId().get("orderStatus"));
	        templateMessage.setToUser("oZ1pk1n_a6zDxhnl3goAdmcFa7qA");

	        List<WxMpTemplateData> data = Arrays.asList(
	                new WxMpTemplateData("first", "亲，请记得收货。"),
	                new WxMpTemplateData("keyword1", "微信点餐"),
	                new WxMpTemplateData("keyword2", "18868812345"),
	                new WxMpTemplateData("keyword3", "fsdfs"),
	                new WxMpTemplateData("keyword4", "sfsd"),
	                new WxMpTemplateData("keyword5", "￥" + "sdfs"),
	                new WxMpTemplateData("remark", "欢迎再次光临！")
	        );
	        templateMessage.setData(data);
	        try {
	            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
	        }catch (WxErrorException e) {
	            
	        }
	  }
    
}


*/