package com.nnk.springboot.controller;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RatingControllerTest {

    @Autowired
    private WebApplicationContext context;


    private MockMvc mvc;

    @Autowired
    private RatingService ratingService;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void shouldReturnRatingListView() throws Exception {

        this.mvc.perform(MockMvcRequestBuilders.get("/poseidon/rating/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("rating/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("listOfRatings"));
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void shouldReturnRatingFormView() throws Exception {

        this.mvc.perform(MockMvcRequestBuilders.get("/poseidon/rating/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("rating/add"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("rating"));
    }

    @Test
    public void shouldValidateForm() throws Exception {

        this.mvc.perform(MockMvcRequestBuilders.post("/poseidon/rating/validate")
                        .param("moodysRating", "MoodyControllerTest")
                        .param("fitchRating", "FitchControllerTest")
                        .param("sandPRating", "SendPRatingControllerTest")
                        .param("orderNumber", "10")
                        .with(SecurityMockMvcRequestPostProcessors.user("user1").roles("USER"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/poseidon/rating/list"));

        Rating ratingExpected = new Rating();

        List<Rating> list = ratingService.findAll();

        for(Rating rat : list)
        {
            if(rat.getMoodysRating().equals("MoodyControllerTest"))
            {
                ratingExpected = rat;
            }
        }
        int ratingID = ratingExpected.getId();

        ratingService.deleteById(ratingID);
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void shouldShowUpdateForm() throws Exception {

        Rating rating = new Rating();
        rating.setMoodysRating("MoodyControllerTest");
        rating.setFitchRating("FitchControllerTest");
        rating.setSandPRating("SendPRatingControllerTest");
        rating.setOrderNumber(10);


        ratingService.saveRating(rating);

        Rating ratingExpected = new Rating();

        List<Rating> list = ratingService.findAll();

        for(Rating rat : list)
        {
            if(rat.getMoodysRating().equals("MoodyControllerTest"))
            {
                ratingExpected = rat;
            }
        }
        int ratingID = ratingExpected.getId();

        this.mvc.perform(MockMvcRequestBuilders.get("/poseidon/rating/update/{id}", ratingID))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("rating/update"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("rating"));

        ratingService.deleteById(ratingID);
    }

    @Test
    public void shouldUpdateRating() throws Exception {

        Rating rating = new Rating();
        rating.setMoodysRating("MoodyControllerTest");
        rating.setFitchRating("FitchControllerTest");
        rating.setSandPRating("SendPRatingControllerTest");
        rating.setOrderNumber(10);

        ratingService.saveRating(rating);

        Rating ratingExpected = new Rating();

        List<Rating> list = ratingService.findAll();

        for(Rating rat : list)
        {
            if(rat.getMoodysRating().equals("MoodyControllerTest"))
            {
                ratingExpected = rat;
            }
        }
        int ratingID = ratingExpected.getId();

        this.mvc.perform(MockMvcRequestBuilders.post("/poseidon/rating/update/{id}", ratingID)
                        .param("moodysRating", "MoodyControllerTest")
                        .param("fitchRating", "FitchControllerTest")
                        .param("sandPRating", "SendPRatingControllerTest")
                        .param("orderNumber", "22")
                        .with(SecurityMockMvcRequestPostProcessors.user("user1").roles("USER"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/poseidon/rating/list"));

        ratingService.deleteById(ratingID);
    }



    @Test
    public void shouldDeleteRating() throws Exception {

        Rating rating = new Rating();
        rating.setMoodysRating("MoodyControllerTest");
        rating.setFitchRating("FitchControllerTest");
        rating.setSandPRating("SendPRatingControllerTest");
        rating.setOrderNumber(10);

        ratingService.saveRating(rating);

        Rating ratingExpected = new Rating();

        List<Rating> list = ratingService.findAll();

        for(Rating rat : list)
        {
            if(rat.getMoodysRating().equals("MoodyControllerTest"))
            {
                ratingExpected = rat;
            }
        }
        int ratingID = ratingExpected.getId();

        this.mvc.perform(MockMvcRequestBuilders.get("/poseidon/rating/delete/{id}", ratingID)
                        .with(SecurityMockMvcRequestPostProcessors.user("user1").roles("USER"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/poseidon/rating/list"));
    }
}
