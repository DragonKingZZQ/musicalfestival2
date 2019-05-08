package com.music.util;

public enum LinkEnum implements CodeEnum{
	SAFETY_POINT(0,"攻略信息"),
	TRANSPORT_COMPUS_INFORMATION(1,"交通露营信息"),
	PLAYLIST(2,"视频链接"),
	BUY_TICKET(3,"购票"),
	SHOP(4,"商店"),
	QUESTIONNAIRE(5,"调查问卷"),
	HANDBOOK_PDF(6,"我的订单"),
	HANDBOOK_HTML(7,"官方支持");
	
	private Integer code;
	private String message;
		
	public Integer getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}

	LinkEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public static LinkEnum getAccountType(int type) {
		switch(type) {
		case 0:
			return SAFETY_POINT;
		case 1:
			return TRANSPORT_COMPUS_INFORMATION;
		case 2:
			return PLAYLIST;
		case 3:
			return BUY_TICKET;
		case 4:
			return SHOP;
		case 5:
			return QUESTIONNAIRE;
		case 6:
			return HANDBOOK_PDF;
		case 7:
			return HANDBOOK_HTML;
		}
		return null;
	}

}
