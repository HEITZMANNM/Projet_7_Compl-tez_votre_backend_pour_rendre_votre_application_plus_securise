package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RuleNameServiceImpl implements  RuleNameService{

    private static final Logger logger = LogManager.getLogger("RuleNameService");

    @Autowired
    private RuleNameRepository ruleNameRepository;
    @Override
    public List<RuleName> findAll() {

        List<RuleName> listOfAllRuleName = new ArrayList<>();

        try
        {
            listOfAllRuleName = ruleNameRepository.findAll();
            logger.debug("The ruleName list was find");
        }
        catch(Exception ex){
            logger.error("Error fetching list of all ruleNames", ex);
        }
        return listOfAllRuleName;
    }

    @Override
    public void save(RuleName ruleName) {

        try
        {
            ruleNameRepository.save(ruleName);
            logger.debug("The ruleName was saved");
        }
        catch(Exception ex){
            logger.error("Error saving ruleName", ex);
        }
    }

    @Override
    public RuleName findById(Integer id) {

        RuleName ruleName= new RuleName();

        try
        {
            ruleName= ruleNameRepository.findById(id).get();
            logger.debug("The ruleName was saved");
        }
        catch(Exception ex){
            logger.error("Error saving ruleName", ex);
        }
        return ruleName;
    }

    @Override
    public void update(RuleName ruleName)
    {
        try
        {
            RuleName ruleNameSaved = ruleNameRepository.findById(ruleName.getId()).get();

            ruleNameSaved.setName(ruleName.getName());
            ruleNameSaved.setDescription(ruleName.getDescription());
            ruleNameSaved.setJson(ruleName.getJson());
            ruleNameSaved.setSql(ruleName.getSql());
            ruleNameSaved.setSqlPart(ruleName.getSqlPart());
            ruleNameSaved.setTemplate(ruleName.getTemplate());

            ruleNameRepository.save(ruleNameSaved);

            logger.debug("The ruleName was find");
        }
        catch(Exception ex){
            logger.error("Error to fetch the ruleName", ex);
        }

    }

    @Override
    public void deleteById(Integer id) {

        try
        {
           ruleNameRepository.deleteById(id);
            logger.debug("The ruleName was deleted");
        }
        catch(Exception ex){
            logger.error("Error deleting ruleName", ex);
        }

    }


}
