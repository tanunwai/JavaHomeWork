package com.demo.javahomework.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.javahomework.service.ParseFromJsonService;
import com.demo.javahomework.util.ParseFromJson;

@Service
public class ParseFromJsonServiceImpl implements ParseFromJsonService{

	@Autowired
	private ParseFromJson parseFromJson;
	
	@Override
	public String qeruyFromJson(String url) throws Exception {		
		return parseFromJson.queryFromJsoin(url);
	}

}