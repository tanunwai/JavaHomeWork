package com.demo.javahomework.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.javahomework.entity.CoinDesk;

public interface CoinDeskRepository extends JpaRepository<CoinDesk, String>{	

	@Query(value="select c from CoinDesk c where c.chartName=?1")
	CoinDesk findByName(String chartName);
}