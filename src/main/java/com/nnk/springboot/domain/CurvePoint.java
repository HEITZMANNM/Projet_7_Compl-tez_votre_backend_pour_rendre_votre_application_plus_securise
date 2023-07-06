package com.nnk.springboot.domain;

import lombok.Data;
import javax.persistence.Id;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "CurvePoint")
public class CurvePoint {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private int id;

    @NotNull(message = "Bid Quantity is mandatory")
    @Column(name = "CurveId")
    private int curveId;

    @Column(name = "asOfDate")
    private Timestamp asOfDate;

    @Column(name = "term")
    private double term;

    @Column(name = "value")
    private double value;

    @Column(name  ="creationDate")
    private Timestamp creationDate;


}