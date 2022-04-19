package com.demo.javahomework.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "i_coindesk")
public class CoinDesk extends BaseEntity{
	
    @Column(nullable = true)
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @Column(name="disclaimer", length = 500, nullable = false)
    private String disclaimer;

    @Column(name="chartname", length = 20, nullable = true)
    private String chartName;

    @OneToMany(mappedBy = "coinDesk", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<BpiEntity> bpi;
}