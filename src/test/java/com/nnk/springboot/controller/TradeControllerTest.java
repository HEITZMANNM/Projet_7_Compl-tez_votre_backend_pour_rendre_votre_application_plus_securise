package com.nnk.springboot.controller;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.RuleNameService;
import com.nnk.springboot.service.TradeService;
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
public class TradeControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private TradeService tradeService;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void shouldReturnTradeListView() throws Exception {

        this.mvc.perform(MockMvcRequestBuilders.get("/poseidon/trade/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("trade/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("listOfAllTrade"));
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void shouldReturnTradeFormView() throws Exception {

        this.mvc.perform(MockMvcRequestBuilders.get("/poseidon/trade/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("trade/add"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("trade"));
    }

    @Test
    public void shouldValidateForm() throws Exception {

        Trade tradeTest = new Trade();
        tradeTest.setType("typeControllerTest");
        tradeTest.setAccount("accountControllerTest");
        tradeTest.setBuyQuantity(10);

        this.mvc.perform(MockMvcRequestBuilders.post("/poseidon/trade/validate", tradeTest)
                        .with(SecurityMockMvcRequestPostProcessors.user("user1").roles("USER"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("trade/add"));

        Trade tradeExpected = new Trade();

        List<Trade> list = tradeService.findAll();

        for(Trade trade : list)
        {
            if(trade.getType().equals("typeControllerTest"))
            {
                tradeExpected = trade;
            }
        }
        int tradeID = tradeExpected.getId();

        tradeService.deleteById(tradeID);
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void shouldShowUpdateForm() throws Exception {

        Trade tradeTest = new Trade();
        tradeTest.setType("typeControllerTest");
        tradeTest.setAccount("accountControllerTest");
        tradeTest.setBuyQuantity(10);


        tradeService.save(tradeTest);

        Trade tradeExpected = new Trade();

        List<Trade> list = tradeService.findAll();

        for(Trade trade : list)
        {
            if(trade.getType().equals("typeControllerTest"))
            {
                tradeExpected = trade;
            }
        }
        int tradeID = tradeExpected.getId();

        this.mvc.perform(MockMvcRequestBuilders.get("/poseidon/trade/update/{id}", tradeID))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("trade/update"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("trade"));

        tradeService.deleteById(tradeID);
    }

    @Test
    public void shouldUpdateTrade() throws Exception {

        Trade tradeTest = new Trade();
        tradeTest.setType("typeControllerTest");
        tradeTest.setAccount("accountControllerTest");
        tradeTest.setBuyQuantity(10);

        tradeService.save(tradeTest);

        Trade tradeExpected = new Trade();

        List<Trade> list = tradeService.findAll();

        for(Trade trade : list)
        {
            if(trade.getType().equals("typeControllerTest"))
            {
                tradeExpected = trade;
            }
        }
        int tradeID = tradeExpected.getId();


        this.mvc.perform(MockMvcRequestBuilders.post("/poseidon/trade/update/{id}", tradeID)
                        .with(SecurityMockMvcRequestPostProcessors.user("user1").roles("USER"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("trade/update"));


        tradeService.deleteById(tradeID);
    }



    @Test
    public void shouldDeleteCurve() throws Exception {


        Trade tradeTest = new Trade();
        tradeTest.setType("typeControllerTest");
        tradeTest.setAccount("accountControllerTest");
        tradeTest.setBuyQuantity(10);

        tradeService.save(tradeTest);

        CurvePoint curvePointExpected = new CurvePoint();

        Trade tradeExpected = new Trade();

        List<Trade> list = tradeService.findAll();

        for(Trade trade : list)
        {
            if(trade.getType().equals("typeControllerTest"))
            {
                tradeExpected = trade;
            }
        }
        int tradeID = tradeExpected.getId();

        this.mvc.perform(MockMvcRequestBuilders.get("/poseidon/trade/delete/{id}", tradeID)
                        .with(SecurityMockMvcRequestPostProcessors.user("user1").roles("USER"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/poseidon/trade/list"));
    }

}
