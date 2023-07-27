package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/poseidon")
public class RatingController {


    @Autowired
    private RatingService ratingService;


    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        List<Rating> listOfRatings = ratingService.findAll();
        model.addAttribute("listOfRatings", listOfRatings);
        log.info("Access to page list of rating was done");
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Model model) {

        Rating rating = new Rating();
        model.addAttribute("rating", rating);


        log.info("Acces to page to add new rating was done");
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid  @ModelAttribute("rating") Rating rating, BindingResult result, Model model) {

        if (!result.hasErrors())
        {
            ratingService.saveRating(rating);
            log.info("The new rating was validated and saved");
            return "redirect:/poseidon/rating/list";
        }
        else
        {
            log.error("errors = " + result.getAllErrors());
            return "rating/add";
        }
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        Rating rating = ratingService.findById(id);
        model.addAttribute("rating", rating);
        log.info("The selected rating was find and access to the page for update rating was done");
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid @ModelAttribute("rating") Rating rating,
                               BindingResult result, Model model) {
        if (!result.hasErrors())
        {
            ratingService.update(rating);
            log.info("The new rating was validated and updated");
            return "redirect:/poseidon/rating/list";
        }
        else
        {
            log.error("errors = " + result.getAllErrors());
            return "rating/update";
        }
    }

    @RequestMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id) {

        ratingService.deleteById(id);
        log.info("The selected rating was deleted");

        return "redirect:/poseidon/rating/list";
    }
}