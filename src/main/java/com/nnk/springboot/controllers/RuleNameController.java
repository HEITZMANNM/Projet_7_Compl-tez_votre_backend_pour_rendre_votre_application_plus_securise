package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
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
public class RuleNameController {

    private static final Logger logger = LogManager.getLogger("RuleNameController");

    @Autowired
    private RuleNameService ruleNameService;


    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        List<RuleName> listOfAllRuleName = ruleNameService.findAll();
        model.addAttribute("listOfAllRuleName", listOfAllRuleName);

        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(Model model)
    {
        RuleName ruleName = new RuleName();
        model.addAttribute("ruleName", ruleName);

        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid @ModelAttribute("ruleName") RuleName ruleName, BindingResult result, Model model)
    {
        if (!result.hasErrors())
        {
            ruleNameService.save(ruleName);
            return "redirect:/poseidon/ruleName/list";
        }
        else
        {
            logger.error("errors = " + result.getAllErrors());
            return "ruleName/add";
        }
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model)
    {
        RuleName ruleName = ruleNameService.findById(id);
        model.addAttribute("ruleName", ruleName);
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid @ModelAttribute("ruleName") RuleName ruleName,
                                 BindingResult result) {

        if (!result.hasErrors())
        {
            ruleNameService.update(ruleName);
            return "redirect:/poseidon/ruleName/list";
        }
        else
        {
            logger.error("errors = " + result.getAllErrors());
            return "ruleName/update";
        }
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {

        ruleNameService.deleteById(id);

        return "redirect:/ruleName/list";
    }
}