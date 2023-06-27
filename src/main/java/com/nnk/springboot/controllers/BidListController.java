package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListServiceImpl;
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
public class BidListController {

    @Autowired
    private BidListServiceImpl bidListServiceImpl;

    private static final Logger logger = LogManager.getLogger("BidListController");

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        List<BidList> listOfAllBids = bidListServiceImpl.getAllBids();
        model.addAttribute("listOfAllBids", listOfAllBids);

        return "bidList/list";
    }


    @GetMapping("/bidList/add")
    public String addBidForm(Model model) {
        BidList bid = new BidList();
        model.addAttribute("bidList", bid);
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid @ModelAttribute("bidList") BidList bid, BindingResult result) {

        if (!result.hasErrors())
        {
            bidListServiceImpl.saveBid(bid);
            return "redirect:/poseidon/bidList/list";
        }
        else
        {
            logger.error("errors = " + result.getAllErrors());
            return "bidList/add";
        }
    }



    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        BidList bid = bidListServiceImpl.getBidById(id);
        model.addAttribute("bidList", bid);

        return "bidList/update";
    }


    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") int id, @Valid @ModelAttribute("bidList") BidList bidList,
                            BindingResult result, Model model) {

        if (!result.hasErrors())
        {
            bidListServiceImpl.update(bidList);
            return "redirect:/poseidon/bidList/list";
        }
        else
        {
            logger.error("errors = " + result.getAllErrors());
            return "bidList/update";
        }
    }

    @RequestMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") int id) {

        bidListServiceImpl.deleteById(id);

        return "redirect:/poseidon/bidList/list";
    }
}