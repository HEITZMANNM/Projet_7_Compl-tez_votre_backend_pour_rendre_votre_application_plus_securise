package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
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
public class TradeController {

    private static final Logger logger = LogManager.getLogger("TradeController");

    @Autowired
    private TradeService tradeService;


    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        List<Trade> listOfAllTrade = tradeService.findAll();
        model.addAttribute("listOfAllTrade", listOfAllTrade);

        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid, Model model) {

        Trade trade = new Trade();
        model.addAttribute("trade", trade);

        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid @ModelAttribute("trade") Trade trade, BindingResult result, Model model) {

        if (!result.hasErrors())
        {
            tradeService.save(trade);
            return "redirect:/poseidon/trade/list";
        }
        else
        {
            logger.error("errors = " + result.getAllErrors());
            return "trade/add";
        }
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        Trade trade = tradeService.findById(id);
        model.addAttribute("trade", trade);

        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid @ModelAttribute("trade") Trade trade,
                              BindingResult result, Model model) {

        if (!result.hasErrors())
        {
            tradeService.upDate(trade);
            return "redirect:/poseidon/trade/list";
        }
        else
        {
            logger.error("errors = " + result.getAllErrors());
            return "trade/upDate";
        }
    }

    @RequestMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id) {

        tradeService.deleteById(id);

        return "redirect:/poseidon/trade/list";
    }
}