package com.nnk.springboot.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

//import org.springframework.data.annotation.Id;
import javax.persistence.Id;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import java.sql.Timestamp;

@Data
@Entity
@DynamicUpdate
@Table(name = "BidList")
public class BidList {
    // TODO: Map columns in data table BIDLIST with corresponding java fields


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BidListId")
    private int id;

    @NotBlank(message = "Account is mandatory")
    @Column(name = "account")
    private String account;

    @NotBlank(message = "Type is mandatory")
    @Column(name = "type")
    private String type;

    @NotNull(message = "Bid Quantity is mandatory")
    @Positive(message = "Bid Quantity must be positive")
    @Column(name = "bidQuantity")
    private Double bidQuantity;

    @Column(name = "askQuantity")
    private Double askQuantity;

    @Column(name = "bid")
    private Double bid;

    @Column(name= "benchmark")
    private String benchmark;

    @Column(name = "bidListDate")
    private Timestamp bidListDate;

    @Column(name = "commentary")
    private String commentary;

    @Column(name = "security")
    private String security;

    @Column(name = "status")
    private String status;

    @Column(name = "trader")
    private String trader;

    @Column(name = "book")
    private String book;

    @Column(name = "creationName")
    private String creationName;

    @Column(name = "creationDate")
    private Timestamp creationDate;

    @Column(name = "revisionName")
    private String revisionName;

    @Column(name = " revisionDate")
    private Timestamp revisionDate;

    @Column(name = "dealName")
    private String dealName;

    @Column(name = "dealType")
    private String dealType;

    @Column(name = "sourceListId")
    private String sourceListId;

    @Column(name = "side")
    private String side;



//    public BidList(String accountTest, String typeTest, double v) {
//    }
//
//    public BidList() {
//
//    }


//    public double getBidQuantity() {
//        return bidQuantity;
//    }
//
//    public int getd() {
//        return id;
//    }
//
//    public void setBidQuantity(double bidQuantity) {
//        this.bidQuantity = bidQuantity;
//    }


}