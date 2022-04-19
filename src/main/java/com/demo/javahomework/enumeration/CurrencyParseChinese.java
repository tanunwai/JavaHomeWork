package com.demo.javahomework.enumeration;

import lombok.Getter;

@Getter
public enum CurrencyParseChinese {

	NT(1, "新臺幣"),
	USD(840, "美元"),
	GBP(826, "英鎊"),
	EUR(978, "歐元");
	
	int code;
	String description;
	
	CurrencyParseChinese(int code, String description){
		this.code=code;
		this.description=description;
	}
}