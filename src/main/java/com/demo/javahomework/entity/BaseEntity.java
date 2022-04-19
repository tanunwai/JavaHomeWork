package com.demo.javahomework.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name="paymentableGenerator", strategy="org.hibernate.id.UUIDGenerator")
    private String id;
}
