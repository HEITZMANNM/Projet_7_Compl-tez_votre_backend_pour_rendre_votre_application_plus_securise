package com.nnk.springboot.integration;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.RatingService;
import com.nnk.springboot.service.RuleNameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class RuleNameServiceIT {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    @Autowired
    private RuleNameService ruleNameService;

    private RuleName ruleNameTest;

    @AfterEach
    public void cleanDataBase()
    {
        List<RuleName> list = ruleNameService.findAll();

        for(RuleName rule : list)
        {
            if(rule.getJson().equals("JsonTest"))
            {
                ruleNameService.deleteById(rule.getId());
            }
        }
    }

    @BeforeEach
    public void setUp()
    {
        ruleNameTest = new RuleName();
        ruleNameTest.setTemplate("TemplateTest");
        ruleNameTest.setName("NameTest");
        ruleNameTest.setSql("SQLTest");
        ruleNameTest.setJson("JsonTest");
        ruleNameTest.setDescription("DescriptionTest");
        ruleNameTest.setSqlPart("SqlPartTest");

        ruleNameService.save(ruleNameTest);
    }

    @Test
    public void testToSaveARuleName()
    {
        RuleName ruleExpected = new RuleName();

        List<RuleName> list = ruleNameService.findAll();

        for(RuleName rule : list)
        {
            if(rule.getJson().equals("JsonTest"))
            {
                ruleExpected = rule;
            }
        }

        assertEquals(ruleExpected.getName(), "NameTest");
    }

    @Test
    public void TestToUpdateRuleName()
    {
        RuleName ruleNameExpected = new RuleName();

        List<RuleName> list = ruleNameService.findAll();

        for(RuleName rule : list)
        {
            if(rule.getJson().equals("JsonTest"))
            {
                ruleNameExpected = rule;
            }
        }

        int ruleExpectedID = ruleNameExpected.getId();

        RuleName ruleUpDate = new RuleName();
        ruleUpDate.setTemplate("TemplateTestUpDated");
        ruleUpDate.setName("NameTest");
        ruleUpDate.setSql("SQLTest");
        ruleUpDate.setJson("JsonTest");
        ruleUpDate.setDescription("DescriptionTest");
        ruleUpDate.setSqlPart("SqlPartTest");
        ruleUpDate.setId(ruleExpectedID);

        ruleNameService.update(ruleUpDate);

        assertEquals(ruleNameService.findById(ruleExpectedID).getTemplate(), "TemplateTestUpDated");

    }

    @Test
    public void testToDeleteRuleById()
    {
        RuleName ruleNameExpected = new RuleName();

        List<RuleName> list = ruleNameService.findAll();

        for(RuleName rule : list)
        {
            if(rule.getJson().equals("JsonTest"))
            {
                ruleNameExpected = rule;
            }
        }

        int ruleExpectedID = ruleNameExpected.getId();

        ruleNameService.deleteById(ruleExpectedID);

        assertNull(ruleNameService.findById(ruleExpectedID).getName());
    }
}
