package com.nnk.springboot.controllers;


import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
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
public class CurveController {

    @Autowired
    private CurvePointService curvePointService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        List<CurvePoint> listOfAllCurvePoints = curvePointService.getAllCurvePoints();
        model.addAttribute("listOfAllCurvePoints", listOfAllCurvePoints);

        log.info("Access to page list of curvepoint was done");

        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(Model model) {

        CurvePoint curvePoint = new CurvePoint();
        model.addAttribute("curvePoint", curvePoint);

        log.info("Acces to page to add new curve point was done");

        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid @ModelAttribute("curvePoint") CurvePoint curvePoint, BindingResult result) {

        if (!result.hasErrors())
        {
            curvePointService.saveCurvePoint(curvePoint);
            log.info("The new curvePoint was validated and saved");
            return "redirect:/poseidon/curvePoint/list";
        }
        else
        {
            log.error("errors = " + result.getAllErrors());
            return "curvePoint/add";
        }
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        CurvePoint curvePoint = curvePointService.getCurvePointById(id);
        model.addAttribute("curvePoint", curvePoint);

        log.info("The selected curvePoint was find and access to the page for update curvePoint was done");

        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") int id, @Valid @ModelAttribute("curvePoint") CurvePoint curvePoint,
                            BindingResult result) {

        if (!result.hasErrors())
        {
            curvePointService.update(curvePoint);
            log.info("The new curvePoint was validated and updated");
            return "redirect:/poseidon/curvePoint/list";
        }
        else
        {
            log.error("errors = " + result.getAllErrors());
            return "curvePoint/update";
        }
    }


    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") int id) {

        curvePointService.deleteById(id);
        log.info("The selected curvePoint was deleted");

        return "redirect:/poseidon/curvePoint/list";
    }
}