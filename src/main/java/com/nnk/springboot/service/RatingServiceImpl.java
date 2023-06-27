package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService{

    private static final Logger logger = LogManager.getLogger("RatingService");

    @Autowired
    private RatingRepository ratingRepository;
    @Override
    public List<Rating> findAll() {

        return ratingRepository.findAll();
    }

    @Override
    public void saveRating(Rating rating) {

        try
        {
            ratingRepository.save(rating);
            logger.debug("The rating was saved");
        }
        catch(Exception ex){
            logger.error("Error saving the rating", ex);
        }

    }

    @Override
    public Rating findById(Integer id) {

        Rating rating = new Rating();

        try
        {
            rating = ratingRepository.findById(id).get();
            logger.debug("The rating was find");
        }
        catch(Exception ex){
            logger.error("Error to fetch the rating", ex);
        }
        return rating;
    }

    @Override
    public void update(Rating rating) {

        try
        {
            Rating ratingSaved = ratingRepository.findById(rating.getId()).get();

            ratingSaved.setFitchRating(rating.getFitchRating());
            ratingSaved.setMoodysRating(rating.getMoodysRating());
            ratingSaved.setSandPRating(rating.getSandPRating());
            ratingSaved.setOrderNumber(rating.getOrderNumber());

            ratingRepository.save(ratingSaved);

            logger.debug("The rating was find");
        }
        catch(Exception ex){
            logger.error("Error to fetch the rating", ex);
        }
    }

    @Override
    public void deleteById(Integer id) {

        try
        {
            ratingRepository.deleteById(id);
            logger.debug("The rating was deleted");
        }
        catch(Exception ex){
            logger.error("Error to delete the rating", ex);
        }

    }
}
