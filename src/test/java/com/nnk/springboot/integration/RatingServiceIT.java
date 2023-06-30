package com.nnk.springboot.integration;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.RatingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
public class RatingServiceIT {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RatingService ratingService;

    private Rating ratingTest;

    @AfterEach
    public void cleanDataBase()
    {
        List<Rating> list = ratingService.findAll();

        for(Rating rating : list)
        {
            if(rating.getMoodysRating().equals("MoodyTest"))
            {
                ratingService.deleteById(rating.getId());
            }
        }
    }

    @BeforeEach
    public void setUp()
    {
        ratingTest = new Rating();
        ratingTest.setMoodysRating("MoodyTest");
        ratingTest.setSandPRating("SandPTest");
        ratingTest.setFitchRating("FitchTest");
        ratingTest.setOrderNumber(10);

        ratingService.saveRating(ratingTest);
    }

    @Test
    public void testToSaveARating()
    {
        Rating ratingExpected = new Rating();

        List<Rating> list = ratingService.findAll();

        for(Rating rating : list)
        {
            if(rating.getFitchRating().equals("FitchTest"))
            {
                ratingExpected = rating;
            }
        }

        assertEquals(ratingExpected.getMoodysRating(), "MoodyTest");
    }

    @Test
    public void TestToUpdate()
    {
        Rating ratingExpected = new Rating();

        List<Rating> list = ratingService.findAll();

        for(Rating rating : list)
        {
            if(rating.getMoodysRating().equals("MoodyTest"))
            {
                ratingExpected = rating;
            }
        }

        int ratingExpectedID = ratingExpected.getId();

        Rating ratingUpDate = new Rating();
        ratingUpDate.setMoodysRating("MoodyTest");
        ratingUpDate.setOrderNumber(20);
        ratingUpDate.setFitchRating("FitchTest");
        ratingUpDate.setSandPRating("SandPTest");
        ratingUpDate.setId(ratingExpectedID);

        ratingService.update(ratingUpDate);

        assertEquals(ratingService.findById(ratingExpectedID).getOrderNumber(), 20);
    }

    @Test
    public void testToDeleteById()
    {
        Rating ratingExpected = new Rating();

        List<Rating> list = ratingService.findAll();

        for(Rating rating : list)
        {
            if(rating.getMoodysRating().equals("MoodyTest"))
            {
                ratingExpected = rating;
            }
        }
        int ratingExpectedIndex = ratingExpected.getId();

        ratingService.deleteById(ratingExpectedIndex);

        assertNull(ratingService.findById(ratingExpectedIndex).getMoodysRating());
    }
}
