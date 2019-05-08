package com.music.util;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientUtil {
    /**
     * 发送get请求
     * @param url（get请求的url）
     * @return
     */
    public static String doGetStr(String accessTokenUrl) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(accessTokenUrl);
        String result=null;
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, "UTF-8");

            }
            httpGet.releaseConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
     /**
     * 发送post请求
     * @param url
     * @param param
     * @return
     */
    public static String doPostStr(String url,String param){
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        String result = null;
        try {
            httpPost.setEntity(new StringEntity(param, "UTF-8"));
            HttpResponse response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}