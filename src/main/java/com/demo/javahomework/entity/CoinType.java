package com.demo.javahomework.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "i_cointype")
public class CoinType extends BaseEntity{

    @Column(name = "code", length = 20, nullable = true)
    private String code;

    @Column(name = "symbol", length = 20, nullable = true)
    private String symbol;

    @Column(name = "rate", nullable = true)
    private String rate;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "rate_float", nullable = true)
    private double rate_float;

    @ManyToOne
    @JoinColumn(name = "bid", referencedColumnName = "id", foreignKey = @ForeignKey(name="i_cointype_fk"))
    private BpiEntity bpiEntity;
}