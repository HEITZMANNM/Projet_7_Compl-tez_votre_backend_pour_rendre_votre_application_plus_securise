package com.nnk.springboot.domain;

import lombok.Data;
import javax.persistence.Id;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "rulename")
public class RuleName {
    // TODO: Map columns in data table RULENAME with corresponding java fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private int id;

    @Column(name = "name")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(name = "description")
    @NotBlank(message = "Description is mandatory")
    private String description;

    @Column(name = "json")
    @NotBlank(message = "Json is mandatory")
    private String json;

    @Column(name = "tempate")
    @NotBlank(message = "Template is mandatory")
    private String template;

    @Column(name = "sqlStr")
    @NotBlank(message = "Sql is mandatory")
    private String sql;

    @Column(name = "sqlPart")
    @NotBlank(message = "SqlPart is mandatory")
    private String sqlPart;

//    public RuleName(String ruleName, String description, String json, String template, String sql, String sqlPart) {
//    }
}