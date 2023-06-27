package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/poseidon")
public class RatingController {

    private static final Logger logger = LogManager.getLogger("RatingController");

    @Autowired
    private RatingService ratingService;


    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        List<Rating> listOfRatings = ratingService.findAll();
        model.addAttribute("listOfRatings", listOfRatings);
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Model model) {

        Rating rating = new Rating();
        model.addAttribute("rating", rating);
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid  @ModelAttribute("rating") Rating rating, BindingResult result, Model model) {

        if (!result.hasErrors())
        {
            ratingService.saveRating(rating);
            return "redirect:/poseidon/rating/list";
        }
        else
        {
            logger.error("errors = " + result.getAllErrors());
            return "rating/add";
        }
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        Rating rating = ratingService.findById(id);
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid @ModelAttribute("rating") Rating rating,
                               BindingResult result, Model model) {
        if (!result.hasErrors())
        {
            ratingService.update(rating);
            return "redirect:/poseidon/rating/list";
        }
        else
        {
            logger.error("errors = " + result.getAllErrors());
            return "rating/update";
        }
    }

    @RequestMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id) {

        ratingService.deleteById(id);

        return "redirect:/poseidon/rating/list";
    }
}