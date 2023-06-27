package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;

import java.util.List;

public interface RatingService {

    public List<Rating> findAll();

    public void saveRating(Rating rating);

   public Rating findById(Integer id);

    public void update(Rating rating);

    public void deleteById(Integer id);
}
