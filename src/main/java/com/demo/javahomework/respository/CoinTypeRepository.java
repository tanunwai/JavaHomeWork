package com.demo.javahomework.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.javahomework.entity.CoinType;

public interface CoinTypeRepository extends JpaRepository<CoinType, String>{
	
}