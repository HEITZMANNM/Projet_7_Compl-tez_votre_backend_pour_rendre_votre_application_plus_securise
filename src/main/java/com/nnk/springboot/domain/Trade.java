package com.nnk.springboot.domain;

import lombok.Data;
import javax.persistence.Id;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "trade")
public class Trade {
    // TODO: Map columns in data table TRADE with corresponding java fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TradeId")
    private int tradeId;


    @Column(name = "account")
    private String account;

    @Column(name = "type")
    private String type;

    @Column(name = "buyQuantity")
    private double buyQuantity;

    @Column(name = "sellQuantity")
    private double sellQuantity;

    @Column(name = "buyPrice")
    private double buyPrice;

    @Column(name = "sellPrice")
    private double sellPrice;

    @Column(name = "tradeDate")
    private Timestamp tradeDate;

    @Column(name = "security")
    private String security;

    @Column(name = "status")
    private String status;

    @Column(name = "trader")
    private String trader;

    @Column(name = "benchmark")
    private String benchmark;

    @Column(name = "book")
    private String book;

    @Column(name = "creationName")
    private String creationName;

    @Column(name = "creationDate")
    private Timestamp creationDate;

    @Column(name = "revisionName")
    private String revisionName;

    @Column(name = "revisionDate")
    private  Timestamp revisionDate;

    @Column(name = "dealName")
    private  String dealName;

    @Column(name = "dealType")
    private String dealType;

    @Column(name = "sourceListId")
    private  String sourceListId;

    @Column(name = "side")
    private String side;

    public Trade(String tradeAccount, String type) {
    }
}