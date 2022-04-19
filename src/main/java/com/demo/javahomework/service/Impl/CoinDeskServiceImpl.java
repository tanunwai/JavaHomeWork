package com.demo.javahomework.service.Impl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.javahomework.POJO.CoinDeskPOJO;
import com.demo.javahomework.POJO.CurrencyNamePOJO;
import com.demo.javahomework.entity.BpiEntity;
import com.demo.javahomework.entity.CoinDesk;
import com.demo.javahomework.entity.CoinType;
import com.demo.javahomework.enumeration.CurrencyParseChinese;
import com.demo.javahomework.respository.BpiRepository;
import com.demo.javahomework.respository.CoinDeskRepository;
import com.demo.javahomework.respository.CoinTypeRepository;
import com.demo.javahomework.service.CoinDeskService;

@Service
public class CoinDeskServiceImpl implements CoinDeskService {

	@Autowired
	private CoinDeskRepository coinDeskRepository;

	@Autowired
	private BpiRepository bpiRepository;

	@Autowired
	private CoinTypeRepository coinTypeRepository;

	@Override
	public ResponseEntity<String> addCoindesk(CoinDeskPOJO coinDeskPOJO) {
		String msg = "";
		if (coinDeskPOJO == null) {
			msg = "Contents Can't be Empty";
			return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
		}
		try {
			CoinDesk coinDesk = new CoinDesk();
			BpiEntity bpiEntity = new BpiEntity();
			CoinType coinType = new CoinType();
			coinDesk.setUpdateTime(coinDeskPOJO.getUpdateTime());
			coinDesk.setDisclaimer(coinDeskPOJO.getDisclaimer());
			coinDesk.setChartName(coinDeskPOJO.getChartName());

			//bpiEntity.setBpiTypeName(coinDeskPOJO.getBpiTypeName());

			coinType.setCode(coinDeskPOJO.getCode());
			coinType.setSymbol(coinDeskPOJO.getSymbol());
			coinType.setRate(coinDeskPOJO.getRate());
			coinType.setDescription(coinDeskPOJO.getDescription());
			coinType.setRate_float(coinDeskPOJO.getRate_float());

			List<BpiEntity> bpiList = new CopyOnWriteArrayList<>();
			List<CoinType> coinTypesList = new CopyOnWriteArrayList<>();
			bpiList.add(bpiEntity);
			coinTypesList.add(coinType);

			bpiEntity.setCoinDesk(coinDesk);
			coinType.setBpiEntity(bpiEntity);
			bpiEntity.setCoinTypes(coinTypesList);
			coinDesk.setBpi(bpiList);

			coinDeskRepository.save(coinDesk);
			bpiRepository.save(bpiEntity);
			coinTypeRepository.save(coinType);

			msg = "Add CoinDesk Successful";
			return new ResponseEntity<>(msg, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@Transactional
	@Override
	public ResponseEntity<List<CoinDeskPOJO>> findAllCoindesk() {
		List<CoinDeskPOJO> coinPOJOList = new CopyOnWriteArrayList<>();
		findAllCoinDesk().forEach(coinJoin -> {
			CoinDeskPOJO coinDeskPOJO = new CoinDeskPOJO();
			coinDeskPOJO.setId(coinJoin.getId());
			coinDeskPOJO.setUpdateTime(((CoinDesk) coinJoin).getUpdateTime());
			coinDeskPOJO.setDisclaimer(((CoinDesk) coinJoin).getDisclaimer());
			coinDeskPOJO.setChartName(((CoinDesk) coinJoin).getChartName());
			findAllBpi().forEach(coinInner -> {
				//coinDeskPOJO.setBpiTypeName(((BpiEntity) coinInner).getBpiTypeName());
				findAllCoinType().forEach(types -> {
					coinDeskPOJO.setCode(((CoinType) types).getCode());
					coinDeskPOJO.setSymbol(((CoinType) types).getSymbol());
					coinDeskPOJO.setDescription(((CoinType) types).getDescription());
					coinDeskPOJO.setRate(((CoinType) types).getRate());
					coinDeskPOJO.setRate_float(((CoinType) types).getRate_float());					
				});
			});
			coinPOJOList.add(coinDeskPOJO);
		});		

		return new ResponseEntity<>(coinPOJOList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> updateCoindesk(CoinDeskPOJO coinDeskPOJO) {
		String msg = "";
		if (coinDeskPOJO == null) {
			msg = "Contents Can't be Empty";
			return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
		}
		boolean flag = coinDeskRepository.findAll().stream()
				.anyMatch(coin -> coin.getId().equals(coinDeskPOJO.getId()));
		if (flag) {
			CoinDesk coinDesk = coinDeskRepository.findAll().stream()
					.filter(coin -> coin.getId().equals(coinDeskPOJO.getId())).findFirst().get();
			coinDesk.setId(coinDeskPOJO.getId());
			coinDesk.setUpdateTime(coinDeskPOJO.getUpdateTime());
			coinDesk.setDisclaimer(coinDeskPOJO.getDisclaimer());
			coinDesk.setChartName(coinDeskPOJO.getChartName());
			String bmpId = findAllBpi().stream().filter(bpi -> bpi.getCoinDesk().getId().equals(coinDesk.getId()))
					.findFirst().get().getId();
			String coinTypeId = findAllCoinType().stream().filter(types -> types.getBpiEntity().getId().equals(bmpId))
					.findFirst().get().getId();

			BpiEntity bpiEntity = new BpiEntity();
			CoinType coinType = new CoinType();

			bpiEntity.setId(bmpId);
			//bpiEntity.setBpiTypeName(coinDeskPOJO.getBpiTypeName());
			bpiEntity.setCoinDesk(coinDesk);
			coinType.setId(coinTypeId);
			coinType.setCode(coinDeskPOJO.getCode());
			coinType.setSymbol(coinDeskPOJO.getSymbol());
			coinType.setRate(coinDeskPOJO.getRate());
			coinType.setDescription(coinDeskPOJO.getDescription());
			coinType.setRate_float(coinDeskPOJO.getRate_float());
			coinType.setBpiEntity(bpiEntity);

			List<BpiEntity> bpiList = new CopyOnWriteArrayList<>();
			List<CoinType> coinTypesList = new CopyOnWriteArrayList<>();

			bpiList.add(bpiEntity);
			coinTypesList.add(coinType);

			bpiEntity.setCoinTypes(coinTypesList);
			coinDesk.setBpi(bpiList);

			coinDeskRepository.save(coinDesk);
			bpiRepository.save(bpiEntity);
			coinTypeRepository.save(coinType);

			msg = "Update CoinDesk Successful";
			return new ResponseEntity<>(msg, HttpStatus.OK);
		}
		msg = "Update CoinDesk Fail";
		return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<String> deleteCoindeskById(String id) {
		try {
			CoinDesk coinDesk = coinDeskRepository.findAll().stream().filter(coin -> coin.getId().equals(id))
					.findFirst().get();
			String bmpId = findAllBpi().stream().filter(bpi -> bpi.getCoinDesk().getId().equals(coinDesk.getId()))
					.findFirst().get().getId();
			String coinTypeId = findAllCoinType().stream().filter(types -> types.getBpiEntity().getId().equals(bmpId))
					.findFirst().get().getId();
			coinTypeRepository.deleteById(coinTypeId);
			coinDeskRepository.deleteById(id);
			if (bpiRepository.existsById(bmpId)) {
				bpiRepository.deleteById(bmpId);
			}
			String msg = "Delete CoinDesk Successful";
			return new ResponseEntity<>(msg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public List<CoinDesk> findAllCoinDesk() {
		return coinDeskRepository.findAll();
	}

	@Override
	public List<BpiEntity> findAllBpi() {
		return bpiRepository.findAll();
	}

	@Override
	public List<CoinType> findAllCoinType() {
		return coinTypeRepository.findAll();
	}

	@Override
	public ResponseEntity<CurrencyNamePOJO> findCoinDeskByName(String chartName) {		
		if(chartName == null) {			
			return null;
		}		
		CoinDesk coinDesk= coinDeskRepository.findByName(chartName);
		String bmpId = findAllBpi().stream().filter(bpi -> bpi.getCoinDesk().getId().equals(coinDesk.getId()))
				.findFirst().get().getId();
		CoinType coinTypes = findAllCoinType().stream().filter(types -> types.getBpiEntity().getId().equals(bmpId))
				.findFirst().get();
		CurrencyNamePOJO currencyNamePOJO=new CurrencyNamePOJO();
		currencyNamePOJO.setUpdateTime(coinDesk.getUpdateTime());
		currencyNamePOJO.setCurrency(coinTypes.getCode());
		for(CurrencyParseChinese c : CurrencyParseChinese.values()) {
			if(c.name().equals(coinTypes.getCode())) {
				currencyNamePOJO.setCurrencyName(c.getDescription());
			}
			String msg="幣別中文名稱不存在請檢查Enum";
			currencyNamePOJO.setCurrencyName(msg);
		}
		currencyNamePOJO.setRate_float(coinTypes.getRate_float());
		return new ResponseEntity<>(currencyNamePOJO, HttpStatus.OK);
	}
}