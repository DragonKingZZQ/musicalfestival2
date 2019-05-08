package com.music.config;

import java.util.Map;

import org.springframework.stereotype.Component;

	@Component
	public class WeChatAccountConfig {

	    /**
	     * 公众平台id
	     */
	    private String mpAppId="wxe8745aea77d797fa";

	    /**
	     * 公众平台密钥
	     */
	    private String mpAppSecret="84904570e33ebe0060ae33d83ad73d1a";

	    /**
	     * 开放平台id
	     */
	    private String openAppId;

	    /**
	     * 开放平台密钥
	     */
	    private String openAppSecret;

	    /**
	     * 商户号
	     */
	    private String mchId;

	    /**
	     * 商户密钥
	     */
	    private String mchKey;

	    /**
	     * 微信模版id
	     */
	    private Map<String, String> templateId;

		public String getMpAppId() {
			return mpAppId;
		}

		public void setMpAppId(String mpAppId) {
			this.mpAppId = mpAppId;
		}

		public String getMpAppSecret() {
			return mpAppSecret;
		}

		public void setMpAppSecret(String mpAppSecret) {
			this.mpAppSecret = mpAppSecret;
		}

		public String getOpenAppId() {
			return openAppId;
		}

		public void setOpenAppId(String openAppId) {
			this.openAppId = openAppId;
		}

		public String getOpenAppSecret() {
			return openAppSecret;
		}

		public void setOpenAppSecret(String openAppSecret) {
			this.openAppSecret = openAppSecret;
		}

		public String getMchId() {
			return mchId;
		}

		public void setMchId(String mchId) {
			this.mchId = mchId;
		}

		public String getMchKey() {
			return mchKey;
		}

		public void setMchKey(String mchKey) {
			this.mchKey = mchKey;
		}

		public Map<String, String> getTemplateId() {
			return templateId;
		}

		public void setTemplateId(Map<String, String> templateId) {
			this.templateId = templateId;
		}
	    
	    
	    
	}


