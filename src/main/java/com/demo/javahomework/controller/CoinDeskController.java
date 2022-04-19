package com.demo.javahomework.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.javahomework.POJO.CoinDeskPOJO;
import com.demo.javahomework.POJO.CurrencyNamePOJO;
import com.demo.javahomework.service.CoinDeskService;
import com.demo.javahomework.service.ParseFromJsonService;

@RestController
@RequestMapping(path= {"/api/v2"})
public class CoinDeskController {
	
	@Autowired
	private CoinDeskService coinDeskService;
	
	@Autowired
	private ParseFromJsonService parseFromJsonService;
	
	@RequestMapping(path= {"/viewnewapi"}, method= {RequestMethod.GET})
	public ResponseEntity<CurrencyNamePOJO> viewNewApi(@RequestParam(value = "chartName") String chartName){
		return coinDeskService.findCoinDeskByName(chartName);
	}
	
	@RequestMapping(path= {"/viewparsejson"}, method= {RequestMethod.GET})
	public ResponseEntity<String> viewParseFromJson(@RequestParam(value = "url") String url) throws Exception{
		String coinJsonOb=parseFromJsonService.qeruyFromJson(url);
		return new ResponseEntity<>(coinJsonOb, HttpStatus.OK);
	}
		
	@RequestMapping(path= {"/listcoin"}, method = {RequestMethod.GET}, produces = "application/json")
	public ResponseEntity<List<CoinDeskPOJO>> queryAllCoinDesk(){
		return coinDeskService.findAllCoindesk();
	}
	
	@RequestMapping(path= {"/addcoin"}, method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addNewCoinDesk(@RequestBody CoinDeskPOJO coinDeskPOJO){
		return coinDeskService.addCoindesk(coinDeskPOJO);
	}
	
	@RequestMapping(path= {"/updatecoin"}, method = {RequestMethod.PATCH}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateCoin(@RequestBody CoinDeskPOJO coinDeskPOJO){
		return coinDeskService.updateCoindesk(coinDeskPOJO);
	}
	
	@RequestMapping(path= {"/deletecoin/{id}"}, method = {RequestMethod.DELETE})
	public ResponseEntity<String> deleteCoin(@PathVariable(name = "id") String id){
		return coinDeskService.deleteCoindeskById(id);
	}

}