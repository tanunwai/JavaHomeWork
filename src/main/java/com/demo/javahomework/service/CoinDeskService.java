package com.demo.javahomework.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.demo.javahomework.POJO.CoinDeskPOJO;
import com.demo.javahomework.POJO.CurrencyNamePOJO;
import com.demo.javahomework.entity.BpiEntity;
import com.demo.javahomework.entity.CoinDesk;
import com.demo.javahomework.entity.CoinType;

public interface CoinDeskService {
	
	ResponseEntity<String> addCoindesk(CoinDeskPOJO coinDeskPOJO);
	
	ResponseEntity<List<CoinDeskPOJO>> findAllCoindesk();
	
	ResponseEntity<String> updateCoindesk(CoinDeskPOJO coinDeskPOJO);
	
	ResponseEntity<String> deleteCoindeskById(String id);
	
	ResponseEntity<CurrencyNamePOJO> findCoinDeskByName(String chartName);
	
	List<CoinDesk> findAllCoinDesk();
	
	List<BpiEntity> findAllBpi();
	
	List<CoinType> findAllCoinType();
}