package com.music.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.music.entity.Midi;
import com.music.entity.Show;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;

public class MidiGZH {
	static {
		// BouncyCastle是一个开源的加解密解决方案，主页在http://www.bouncycastle.org/
		Security.addProvider(new BouncyCastleProvider());
	}

	/**
	 * 获取access_token
	 * 
	 * @param appid
	 *            服务号id
	 * @param secret
	 *            服务号密匙
	 * @return access_token
	 */
	public String gettoken(String appid, String secret) {
		// 获取access_token
		String gettoken = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
				+ appid + "&secret=" + secret;
		// 发送请求
		String token = HttpClientUtil.doGetStr(gettoken);
		JSONObject jsontoken = JSON.parseObject(token);
		System.out.println("gettoken=" + jsontoken);
		// 得到token
		String access_token = jsontoken.getString("access_token");
		// 获取用户列表

		return access_token;
	}

	/**
	 * 获取用户列表
	 * 
	 * @param access_token
	 *            服务号token
	 * @param next_openid
	 *            从数据库服务号表获取next_openid
	 * @return Map<String,Object> String map.get("next_openid"):新的next_openid
	 *         List<GZH> map.get("list"):用户列表 GZH:公众号bean
	 */
	public Map<String, Object> getlist(String access_token, String next_openid) {

		/*
		 * access_token:调用接口凭证 next_openid: 第一个拉取的OPENID，不填默认从头开始拉取
		 */
		String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=";
		String next_openidtype = "&next_openid=";

		List<Midi> list = new ArrayList<Midi>();
		// 发送请求
		if (next_openid == null) {
			String result = HttpClientUtil.doGetStr(url + access_token);
			JSONObject jsonresult = JSON.parseObject(result);
			JSONObject data = jsonresult.getJSONObject("data");
			JSONArray opendis = data.getJSONArray("openid");
			int total = jsonresult.getInteger("total");
			int count = jsonresult.getInteger("count");
			next_openid = jsonresult.getString("next_openid");

			for (int i = 0; i < opendis.size(); i++) {
				Midi gzh = new Midi();
				gzh.setOpenid(opendis.get(i).toString());
				list.add(gzh);
			}
			while (count == 10000) {
				result = HttpClientUtil.doGetStr(url + access_token
						+ next_openidtype + next_openid);
				jsonresult = JSON.parseObject(result);
				data = jsonresult.getJSONObject("data");
				opendis = data.getJSONArray("openid");
				total = jsonresult.getInteger("total");
				count = jsonresult.getInteger("count");
				next_openid = jsonresult.getString("next_openid");
				for (int i = 0; i < opendis.size(); i++) {
					Midi gzh = new Midi();
					gzh.setOpenid(opendis.get(i).toString());
					list.add(gzh);
				}
			}
		} else {
			String result = HttpClientUtil.doGetStr(url + access_token
					+ next_openidtype + next_openid);
			JSONObject jsonresult = JSON.parseObject(result);
			System.out.println(jsonresult);
			if (jsonresult.getInteger("errcode") == null) {
				int count = jsonresult.getInteger("count");
				if (count > 0) {
					JSONObject data = jsonresult.getJSONObject("data");
					JSONArray opendis = data.getJSONArray("openid");
					int total = jsonresult.getInteger("total");
					next_openid = jsonresult.getString("next_openid");

					for (int i = 0; i < opendis.size(); i++) {
						Midi gzh = new Midi();
						gzh.setOpenid(opendis.get(i).toString());
						list.add(gzh);
					}
					while (count == 10000) {
						result = HttpClientUtil.doGetStr(url + access_token
								+ next_openidtype + next_openid);
						jsonresult = JSON.parseObject(result);
						data = jsonresult.getJSONObject("data");
						opendis = data.getJSONArray("openid");
						total = jsonresult.getInteger("total");
						count = jsonresult.getInteger("count");
						next_openid = jsonresult.getString("next_openid");
						for (int i = 0; i < opendis.size(); i++) {
							Midi gzh = new Midi();
							gzh.setOpenid(opendis.get(i).toString());
							list.add(gzh);
						}
					}
				}
			}
		}
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("next_openid", next_openid);
		maps.put("list", list);
		return maps;
	}

	/**
	 * 获取服务号用户的unionid
	 * 
	 * @param openid
	 *            服务号用户openid
	 * @param access_token
	 *            服务号access_token
	 * @return String 服务号用户的unionid
	 */
	public String getUserUnionid(String openid, String access_token) {
		// 发送请求
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="
				+ access_token + "&openid=" + openid + "&lang=zh_CN";
		/*
		 * { "subscribe": 1, "openid": "o6_bmjrPTlm6_2sgVt7hMZOPfL2M",
		 * "nickname": "Band", "sex": 1, "language": "zh_CN", "city": "广州",
		 * "province": "广东", "country": "中国", "headimgurl":
		 * "http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0"
		 * , "subscribe_time": 1382694957, "unionid":
		 * " o6_bmasdasdsad6_2sgVt7hMZOPfL" "remark": "", "groupid": 0,
		 * "tagid_list":[128,2], "subscribe_scene": "ADD_SCENE_QR_CODE",
		 * "qr_scene": 98765, "qr_scene_str": "" }
		 */

		String result = HttpClientUtil.doGetStr(url);
		JSONObject resultJsonObject = JSON.parseObject(result);
		System.out.println(resultJsonObject);
		String unionid = resultJsonObject.getString("unionid");
		return unionid;
	}

	//

	/**
	 * 获取全部演出的id以及演出开始时间戳
	 * 
	 * @param shows
	 *            全部演出的列表 List<Show>
	 * @return map<"perid",String> 演出id map<"Date",long> 演出时间戳
	 */
	public List<Map<String, Object>> getShowTimeandID(List<Show> shows) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < shows.size(); i++) {
			String show_date = shows.get(i).getStage_time();
			String show_time = shows.get(i).getShow_time();
			String[] show_times = show_time.split("-");
			String time = show_date + show_times[0];
			// System.out.println(time);
			DateFormat df = new SimpleDateFormat("yyyy-MM-ddhh:mm:ss");
			try {
				Date dt1 = df.parse(time); // 年月日演出时间
				// System.out.println(dt1.getTime());
				map.put("perid", shows.get(i).getId());
				map.put("Date", dt1.getTime());
				list.add(map);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map = new HashMap<String, Object>();
			map.clear();
		}
		return list;
	}

	/**
	 * 获取用户信息
	 * 
	 * @param encryptedData
	 * @param sessionKey
	 * @param iv
	 * @return
	 */
	public static JSONObject getUserInfo(String encryptedData,
			String sessionKey, String iv) {
		// 被加密的数据
		byte[] dataByte = com.sun.org.apache.xerces.internal.impl.dv.util.Base64
				.decode(encryptedData);
		// 加密秘钥
		byte[] keyByte = com.sun.org.apache.xerces.internal.impl.dv.util.Base64
				.decode(sessionKey);
		// 偏移量
		byte[] ivByte = com.sun.org.apache.xerces.internal.impl.dv.util.Base64
				.decode(iv);

		try {
			// 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
			int base = 16;
			if (keyByte.length % base != 0) {
				int groups = keyByte.length / base
						+ (keyByte.length % base != 0 ? 1 : 0);
				byte[] temp = new byte[groups * base];
				Arrays.fill(temp, (byte) 0);
				System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
				keyByte = temp;
			}
			// 初始化
			Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
			SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
			AlgorithmParameters parameters = AlgorithmParameters
					.getInstance("AES");
			parameters.init(new IvParameterSpec(ivByte));
			cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
			byte[] resultByte = cipher.doFinal(dataByte);
			if (null != resultByte && resultByte.length > 0) {
				String result = new String(resultByte, "UTF-8");
				return JSONObject.parseObject(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * AES解密
	 *
	 * @param data
	 *            //密文，被加密的数据
	 * @param key
	 *            //秘钥
	 * @param iv
	 *            //偏移量
	 * @param encodingFormat
	 *            //解密后的结果需要进行的编码
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String data, String key, String iv,
			String encodingFormat) throws Exception {
		// initialize();

		// 被加密的数据
		byte[] dataByte = Base64.decodeBase64(data);
		// 加密秘钥
		byte[] keyByte = Base64.decodeBase64(key);
		// 偏移量
		byte[] ivByte = Base64.decodeBase64(iv);

		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");

			SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");

			AlgorithmParameters parameters = AlgorithmParameters
					.getInstance("AES");
			parameters.init(new IvParameterSpec(ivByte));

			cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化

			byte[] resultByte = cipher.doFinal(dataByte);
			if (null != resultByte && resultByte.length > 0) {
				String result = new String(resultByte, encodingFormat);
				return result;
			}
			return null;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidParameterSpecException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return null;
	}

}
