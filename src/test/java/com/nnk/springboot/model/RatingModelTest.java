package com.nnk.springboot.model;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RatingModelTest {

    @Test
    public void testHashCode()
    {
        Rating rating = new Rating();
        rating.setSandPRating("sandPRating");
        rating.setFitchRating("fitch");
        rating.setMoodysRating("moodys");
        rating.setOrderNumber(10);
        rating.setId(1);

        assertNotNull(rating.hashCode());

    }

    @Test
    public void testEquals()
    {
        Rating rating = new Rating();
        rating.setSandPRating("sandPRating");
        rating.setFitchRating("fitch");
        rating.setMoodysRating("moodys");
        rating.setOrderNumber(10);
        rating.setId(1);

        Rating rating2 = new Rating();
        rating2.setSandPRating("sandPRating");
        rating2.setFitchRating("fitch");
        rating2.setMoodysRating("moodys");
        rating2.setOrderNumber(10);
        rating2.setId(1);

        Rating rating3 = new Rating();
        rating3.setSandPRating("sandPRating");
        rating3.setFitchRating("fitch");
        rating3.setMoodysRating("moodys");
        rating3.setOrderNumber(20);
        rating3.setId(1);


        assertTrue(rating.equals(rating));

        assertTrue(rating.equals(rating2));

        rating2 = null;

        assertFalse(rating.equals(rating2));

        assertFalse((new Rating().equals(rating)));

        assertFalse(rating.equals(rating3));

        rating3.setOrderNumber(10);

        rating3.setMoodysRating("moodys ratin3");

        assertFalse(rating.equals(rating3));
    }

    @Test
    public void testToString()
    {
        Rating rating3 = new Rating();
        rating3.setSandPRating("sandPRating");
        rating3.setFitchRating("fitch");
        rating3.setMoodysRating("moodys");
        rating3.setOrderNumber(20);
        rating3.setId(1);

        assertNotNull(rating3.toString());
    }

}
