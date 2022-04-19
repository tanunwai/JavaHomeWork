package com.demo.javahomework.POJO;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CoinDeskPOJO {
	
	private String id;
	
	@JsonFormat(pattern = "yyyy/MM/dd HH:ss:dd")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:ss:dd")
	private Date updateTime;

	private String disclaimer;
	
	private String chartName;
	
	//private String bpiTypeName;
	
	private String code;
	
	private String symbol;
	
	private String rate;
	
	private String description;
	
	private double rate_float;
}