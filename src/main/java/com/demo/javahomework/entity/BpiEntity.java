package com.demo.javahomework.entity;

import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="i_bpientity")
public class BpiEntity extends BaseEntity{
	/*
    @Column(name="bpitypename", length = 10, nullable = true)
    private String bpiTypeName;*/

    @OneToMany(mappedBy = "bpiEntity" , cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CoinType> coinTypes;

    @ManyToOne
    @JoinColumn(name = "cid", referencedColumnName = "id", foreignKey = @ForeignKey(name="i_bpientity_fk"))
    private CoinDesk coinDesk;
}