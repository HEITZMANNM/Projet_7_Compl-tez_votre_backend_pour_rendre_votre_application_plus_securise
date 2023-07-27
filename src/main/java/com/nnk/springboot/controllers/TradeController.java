package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
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
public class TradeController {


    @Autowired
    private TradeService tradeService;


    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        List<Trade> listOfAllTrade = tradeService.findAll();
        model.addAttribute("listOfAllTrade", listOfAllTrade);
        log.info("Access to page list of Trade was done");

        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid, Model model) {

        Trade trade = new Trade();
        model.addAttribute("trade", trade);
        log.info("Acces to page to add new Trade was done");

        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid @ModelAttribute("trade") Trade trade, BindingResult result, Model model) {

        if (!result.hasErrors())
        {
            tradeService.save(trade);
            log.info("The new Trade was validated and saved");
            return "redirect:/poseidon/trade/list";
        }
        else
        {
            log.error("errors = " + result.getAllErrors());
            return "trade/add";
        }
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        Trade trade = tradeService.findById(id);
        model.addAttribute("trade", trade);
        log.info("The selected Trade was find and access to the page for update Trade was done");

        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid @ModelAttribute("trade") Trade trade,
                              BindingResult result, Model model) {

        if (!result.hasErrors())
        {
            tradeService.upDate(trade);
            log.info("The Trade was validated and updated");
            return "redirect:/poseidon/trade/list";
        }
        else
        {
            log.error("errors = " + result.getAllErrors());
            return "trade/update";
        }
    }

    @RequestMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id) {

        tradeService.deleteById(id);
        log.info("The selected Trade was deleted");

        return "redirect:/poseidon/trade/list";
    }
}