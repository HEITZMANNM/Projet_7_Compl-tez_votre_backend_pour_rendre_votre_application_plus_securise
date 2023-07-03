package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
import com.nnk.springboot.service.CurvePointServiceImpl;
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
public class CurveController {

    private static final Logger logger = LogManager.getLogger("CurvePointController");
    @Autowired
    private CurvePointService curvePointService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        List<CurvePoint> listOfAllCurvePoints = curvePointService.getAllCurvePoints();
        model.addAttribute("listOfAllCurvePoints", listOfAllCurvePoints);

        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(Model model) {

        CurvePoint curvePoint = new CurvePoint();
        model.addAttribute("curvePoint", curvePoint);

        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid @ModelAttribute("curvePoint") CurvePoint curvePoint, BindingResult result) {

        if (!result.hasErrors())
        {
            curvePointService.saveCurvePoint(curvePoint);
            return "redirect:/poseidon/curvePoint/list";
        }
        else
        {
            logger.error("errors = " + result.getAllErrors());
            return "curvePoint/add";
        }
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        CurvePoint curvePoint = curvePointService.getCurvePointById(id);
        model.addAttribute("curvePoint", curvePoint);

        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") int id, @Valid @ModelAttribute("curvePoint") CurvePoint curvePoint,
                            BindingResult result) {

        if (!result.hasErrors())
        {
            curvePointService.update(curvePoint);
            return "redirect:/poseidon/curvePoint/list";
        }
        else
        {
            logger.error("errors = " + result.getAllErrors());
            return "curvePoint/update";
        }
    }


    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") int id) {

        curvePointService.deleteById(id);

        return "redirect:/poseidon/curvePoint/list";
    }
}