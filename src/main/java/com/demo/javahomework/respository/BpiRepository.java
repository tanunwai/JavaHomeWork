package com.demo.javahomework.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.javahomework.entity.BpiEntity;

public interface BpiRepository extends JpaRepository<BpiEntity, String>{
	
}