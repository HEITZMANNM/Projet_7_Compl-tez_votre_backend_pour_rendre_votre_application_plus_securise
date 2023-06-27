package com.nnk.springboot.domain;

import lombok.Data;
import javax.persistence.Id;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "rating")
public class Rating {
    // TODO: Map columns in data table RATING with corresponding java fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private int id;

    @Column(name = "moodysRating")
    @NotBlank(message = "MoodysRating is mandatory")
    private String moodysRating;

    @Column(name = "sandPRating")
    @NotBlank(message = "SandPRating is mandatory")
    private String sandPRating;

    @Column(name = "fitchRating")
    @NotBlank(message = "FitchRating is mandatory")
    private String fitchRating;

    @Column(name = "orderNumber")
    @NotNull(message = "Order is mandatory")
    @NotNull(message = "Order must be positive")
    private int orderNumber;

//    public Rating(String moodysRating, String sandPRating, String fitchRating, int i) {
//    }
}