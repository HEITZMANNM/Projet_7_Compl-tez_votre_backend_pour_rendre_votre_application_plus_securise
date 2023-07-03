package com.nnk.springboot.controller;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.CurvePointService;
import com.nnk.springboot.service.RuleNameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RuleNameControllerTest {


    @Autowired
    private WebApplicationContext context;


    private MockMvc mvc;

    @Autowired
    private RuleNameService ruleNameService;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void shouldReturnRuleNameListView() throws Exception {

        this.mvc.perform(MockMvcRequestBuilders.get("/poseidon/ruleName/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("ruleName/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("listOfAllRuleName"));
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void shouldReturnRuleNameFormView() throws Exception {

        this.mvc.perform(MockMvcRequestBuilders.get("/poseidon/ruleName/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("ruleName/add"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("ruleName"));
    }

    @Test
    public void shouldValidateForm() throws Exception {

        RuleName ruleName = new RuleName();
        ruleName.setSqlPart("SQLPartControllerTest");
        ruleName.setName("NameControllertest");
        ruleName.setSql("SQLControllerTest");
        ruleName.setJson("JsonControllerTest");
        ruleName.setTemplate("TemplateControllerTest");
        ruleName.setDescription("DescriptionControllertest");

        this.mvc.perform(MockMvcRequestBuilders.post("/poseidon/ruleName/validate", ruleName)
                        .with(SecurityMockMvcRequestPostProcessors.user("user1").roles("USER"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("ruleName/add"));

        RuleName ruleNameExpected = new RuleName();

        List<RuleName> list = ruleNameService.findAll();

        for(RuleName rule : list)
        {
            if(rule.getJson().equals("JsonControllerTest"))
            {
                ruleNameExpected = rule;
            }
        }
        int ruleNameID = ruleNameExpected.getId();

        ruleNameService.deleteById(ruleNameID);
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void shouldShowUpdateForm() throws Exception {

        RuleName ruleName = new RuleName();
        ruleName.setSqlPart("SQLPartControllerTest");
        ruleName.setName("NameControllertest");
        ruleName.setSql("SQLControllerTest");
        ruleName.setJson("JsonControllerTest");
        ruleName.setTemplate("TemplateControllerTest");
        ruleName.setDescription("DescriptionControllertest");


        ruleNameService.save(ruleName);

        RuleName ruleNameExpected = new RuleName();

        List<RuleName> list = ruleNameService.findAll();

        for(RuleName rule : list)
        {
            if(rule.getJson().equals("JsonControllerTest"))
            {
                ruleNameExpected = rule;
            }
        }
        int ruleNameID = ruleNameExpected.getId();

        this.mvc.perform(MockMvcRequestBuilders.get("/poseidon/ruleName/update/{id}", ruleNameID))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("ruleName/update"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("ruleName"));

        ruleNameService.deleteById(ruleNameID);
    }

    @Test
    public void shouldUpdateRuleName() throws Exception {

        RuleName ruleName = new RuleName();
        ruleName.setSqlPart("SQLPartControllerTest");
        ruleName.setName("NameControllertest");
        ruleName.setSql("SQLControllerTest");
        ruleName.setJson("JsonControllerTest");
        ruleName.setTemplate("TemplateControllerTest");
        ruleName.setDescription("DescriptionControllertest");

        ruleNameService.save(ruleName);


        this.mvc.perform(MockMvcRequestBuilders.post("/poseidon/ruleName/update/{id}", 1)
                        .with(SecurityMockMvcRequestPostProcessors.user("user1").roles("USER"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("ruleName/update"));

        RuleName ruleNameExpected = new RuleName();

        List<RuleName> list = ruleNameService.findAll();

        for(RuleName rule : list)
        {
            if(rule.getJson().equals("JsonControllerTest"))
            {
                ruleNameExpected = rule;
            }
        }
        int ruleNameID = ruleNameExpected.getId();

        ruleNameService.deleteById(ruleNameID);
    }



    @Test
    public void shouldDeleteCurve() throws Exception {


        RuleName ruleName = new RuleName();
        ruleName.setSqlPart("SQLPartControllerTest");
        ruleName.setName("NameControllertest");
        ruleName.setSql("SQLControllerTest");
        ruleName.setJson("JsonControllerTest");
        ruleName.setTemplate("TemplateControllerTest");
        ruleName.setDescription("DescriptionControllertest");

        ruleNameService.save(ruleName);

        CurvePoint curvePointExpected = new CurvePoint();

        RuleName ruleNameExpected = new RuleName();

        List<RuleName> list = ruleNameService.findAll();

        for(RuleName rule : list)
        {
            if(rule.getJson().equals("JsonControllerTest"))
            {
                ruleNameExpected = rule;
            }
        }
        int ruleNameID = ruleNameExpected.getId();

        this.mvc.perform(MockMvcRequestBuilders.get("/poseidon/ruleName/delete/{id}", ruleNameID)
                        .with(SecurityMockMvcRequestPostProcessors.user("user1").roles("USER"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/ruleName/list"));
    }
}
