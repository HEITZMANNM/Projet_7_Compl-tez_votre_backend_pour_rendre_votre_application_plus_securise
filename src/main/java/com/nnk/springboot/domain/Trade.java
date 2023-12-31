package com.nnk.springboot.domain;

import lombok.Data;
import javax.persistence.Id;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "Trade")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TradeId")
    private int id;

    @Column(name = "account")
    @NotBlank(message = "Account is mandatory")
    private String account;

    @Column(name = "type")
    @NotBlank(message = "Type is mandatory")
    private String type;

    @Column(name = "buyQuantity")
    @NotNull(message = "Buy Quantity is mandatory")
    @Positive(message = "Buy Quantity must be positive")
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

}