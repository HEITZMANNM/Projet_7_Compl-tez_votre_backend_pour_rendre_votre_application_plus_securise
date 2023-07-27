package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
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
public class BidListController {

    @Autowired
    private BidListService bidListService;



    @RequestMapping("/bidList/list")
    public String home(Model model)
    {

        List<BidList> listOfAllBids = bidListService.getAllBids();
        model.addAttribute("listOfAllBids", listOfAllBids);

        log.info("Acces to page list of bid was done");
        return "bidList/list";

    }


    @GetMapping("/bidList/add")
    public String addBidForm(Model model) {
        BidList bid = new BidList();
        model.addAttribute("bidList", bid);

        log.info("Acces to page to add new bid was done");

        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid @ModelAttribute("bidList") BidList bid, BindingResult result) {

        if (!result.hasErrors())
        {
            bidListService.saveBid(bid);

            log.info("The new bid was validated and saved");

            return "redirect:/poseidon/bidList/list";
        }
        else
        {
            log.error("errors = " + result.getAllErrors());
            return "bidList/add";
        }
    }



    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        BidList bid = bidListService.getBidById(id);
        model.addAttribute("bidList", bid);

        log.info("The selected bid was find and access to the page for update bid was done");

        return "bidList/update";
    }


    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") int id, @Valid @ModelAttribute("bidList") BidList bidList,
                            BindingResult result, Model model) {

        if (!result.hasErrors())
        {
            bidListService.update(bidList);
            log.info("The new bid was validated and updated");
            return "redirect:/poseidon/bidList/list";
        }
        else
        {
            log.error("errors = " + result.getAllErrors());
            return "bidList/update";
        }
    }

    @RequestMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") int id) {

        bidListService.deleteById(id);

        log.info("The selected bid was deleted");

        return "redirect:/poseidon/bidList/list";
    }
}