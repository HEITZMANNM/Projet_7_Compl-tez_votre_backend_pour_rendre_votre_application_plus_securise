package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.RatingService;
import com.nnk.springboot.service.RuleNameService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
public class RuleNameServiceTest {

    @MockBean
    private RuleNameRepository ruleNameRepository;

    @Autowired
    private RuleNameService ruleNameService;

    private RuleName ruleNameTest;

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

        List<RuleName> ruleNameList = new ArrayList<>();
        ruleNameList.add(ruleNameTest);

        when(ruleNameRepository.findAll()).thenReturn(ruleNameList);
        when(ruleNameRepository.findById(any())).thenReturn(Optional.ofNullable(ruleNameTest));

    }

    @Test
    public void testToGetAllRule()
    {
        List<RuleName> list = ruleNameService.findAll();

        assertEquals(list.get(0).getName(), "NameTest");
    }

    @Test
    public void testToGetRuleById()
    {
        RuleName ruleNameExpected = ruleNameService.findById(1);

        assertEquals(ruleNameExpected.getJson(), "JsonTest");
    }

}
