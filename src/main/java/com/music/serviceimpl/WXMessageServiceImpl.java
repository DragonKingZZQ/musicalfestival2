package com.music.serviceimpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.config.WeChatAccountConfig;
import com.music.dao.NextopenidDao;
import com.music.entity.Nextopenid;
import com.music.service.WXMessageService;




import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;


@Service
public class WXMessageServiceImpl  implements WXMessageService{
	//依赖持久层：注入

	    @Autowired
	    private WxMpService wwxMpService;
	    
	    @Autowired
	    private WeChatAccountConfig weChatAccountConfig;
	    
	   @Autowired
	   private NextopenidDao nextopenidDao;

	    public void sendMessage(String userOpenId) { 
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
		String TemplateId = pro.get("TemplateId").toString();
		//System.out.println("模板打啊打"+TemplateId);
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        //模板id
        templateMessage.setTemplateId(TemplateId);
        templateMessage.setToUser(userOpenId);

        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "您已设置演出提醒!"),
                new WxMpTemplateData("keyword1", ""),
                new WxMpTemplateData("keyword2", ""),
                new WxMpTemplateData("keyword3", ""),
                new WxMpTemplateData("keyword4", ""),
                new WxMpTemplateData("remark", "您已设置演出提醒!")
        );
        templateMessage.setData(data);
        try {
        	 String dage = wwxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        	 //System.out.println(dage+"大哥你好吗");
        }catch (WxErrorException e) {
            
        }
	}
	    public void sendMessage(String userOpenId, List<WxMpTemplateData> data) { 
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
		String TemplateId = pro.get("TemplateId").toString();
		//System.out.println("模板打啊打"+TemplateId);
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        //模板id
        templateMessage.setTemplateId(TemplateId);
        templateMessage.setToUser(userOpenId);

//        List<WxMpTemplateData> data = Arrays.asList(
//                new WxMpTemplateData("first", "测试收到了告诉我。"),
//                new WxMpTemplateData("keyword1", "大哥你好吗"),
//                new WxMpTemplateData("keyword2", "小弟我心很累"),
//                new WxMpTemplateData("keyword3", "大哥求你了"),
//                new WxMpTemplateData("keyword4", "你肯定收到了"),
//                new WxMpTemplateData("keyword5", "耶，简直太开心" + "sdfs"),
//                new WxMpTemplateData("remark", "欢迎再次光临！")
//        );
        templateMessage.setData(data);
        try {
        	 String dage = wwxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        	 //System.out.println(dage+"大哥你好吗");
        }catch (WxErrorException e) {
            System.out.println("未知错误");
        }
	}


		@Override
		public List<Nextopenid> findAllnextopenid() {
			// TODO Auto-generated method stub
			return nextopenidDao.findAllnextopenid();
		}


		@Override
		public int updateNextopenidById(String id) {
			// TODO Auto-generated method stub
			return nextopenidDao.updateNextopenidById(id);
		}


		@Override
		public int insertNextopenid(Nextopenid nextopenid) {
			// TODO Auto-generated method stub
			return nextopenidDao.insertNextopenid(nextopenid);
		}

	
}
