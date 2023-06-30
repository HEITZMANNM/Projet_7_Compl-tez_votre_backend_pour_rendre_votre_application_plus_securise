package com.nnk.springboot;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.CurvePointService;
import com.nnk.springboot.service.RatingService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@SpringBootTest
public class RatingServiceTest {

    @MockBean
    private RatingRepository ratingRepository;

    @Autowired
    private RatingService ratingService;

    private Rating ratingTest;

    @BeforeEach
    public void setUp()
    {
        ratingTest = new Rating();
        ratingTest.setMoodysRating("MoodyTest");
        ratingTest.setSandPRating("SandPTest");
        ratingTest.setFitchRating("FitchTest");
        ratingTest.setOrderNumber(10);


        List<Rating> ratingList = new ArrayList<>();
        ratingList.add(ratingTest);

        when(ratingRepository.findAll()).thenReturn(ratingList);
        when(ratingRepository.findById(any())).thenReturn(Optional.ofNullable(ratingTest));

    }

    @Test
    public void testToGetAllRatings()
    {
        List<Rating> list = ratingService.findAll();

        assertEquals(list.get(0).getMoodysRating(), "MoodyTest");
    }

    @Test
    public void testToGetRatingById()
    {
        Rating ratingExpected = ratingService.findById(1);

        assertEquals(ratingExpected.getFitchRating(), "FitchTest");
    }
}
