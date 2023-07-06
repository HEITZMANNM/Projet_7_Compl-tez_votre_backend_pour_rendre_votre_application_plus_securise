package com.nnk.springboot.domain;

import lombok.Data;
import javax.persistence.Id;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "Rulename")
public class RuleName {

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

}