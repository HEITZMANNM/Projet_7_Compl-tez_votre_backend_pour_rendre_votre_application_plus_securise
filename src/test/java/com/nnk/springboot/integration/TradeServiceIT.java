package com.nnk.springboot.integration;


import com.nnk.springboot.domain.Trade;

import com.nnk.springboot.repositories.TradeRepository;

import com.nnk.springboot.service.TradeService;
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
public class TradeServiceIT {

    @Autowired
    TradeService tradeService;

    @Autowired
    TradeRepository tradeRepository;

    private Trade tradeTest;


    @AfterEach
    public void cleanDataBase()
    {
        List<Trade> list = tradeService.findAll();

        for(Trade trade : list)
        {
            if(trade.getAccount().equals("Account Test"))
            {
                tradeService.deleteById(trade.getId());
            }
        }
    }

    @BeforeEach
    public void setUp()
    {
        tradeTest = new Trade();
        tradeTest.setBuyQuantity(10);
        tradeTest.setType("Type test");
        tradeTest.setAccount("Account Test");

        tradeService.save(tradeTest);
    }

    @Test
    public void testToSaveATrade()
    {
        Trade tradeExpected = new Trade();

        List<Trade> list = tradeService.findAll();

        for(Trade trade : list)
        {
            if(trade.getAccount().equals("Account Test"))
            {
                tradeExpected = trade;
            }
        }

        assertEquals(tradeExpected.getType(), "Type test");
        assertEquals(tradeExpected.getBuyQuantity(), 10);
    }

    @Test
    public void TestToUpdateTrade()
    {
        Trade tradeExpected = new Trade();

        List<Trade> list = tradeService.findAll();

        for(Trade trade : list)
        {
            if(trade.getAccount().equals("Account Test"))
            {
                tradeExpected = trade;
            }
        }

        int tradeExpectedID = tradeExpected.getId();

        Trade tradeUpDated = new Trade();
        tradeUpDated.setAccount("Account Test");
        tradeUpDated.setBuyQuantity(20);
        tradeUpDated.setType("Type Test Updated");
        tradeUpDated.setId(tradeExpectedID);

        tradeService.upDate(tradeUpDated);

        assertEquals(tradeService.findById(tradeExpectedID).getBuyQuantity(), 20);
        assertEquals(tradeService.findById(tradeExpectedID).getType(), "Type Test Updated");
    }

    @Test
    public void testToDeleteById()
    {
        Trade tradeExpected = new Trade();

        List<Trade> list = tradeService.findAll();

        for(Trade trade : list)
        {
            if(trade.getAccount().equals("Account Test"))
            {
                tradeExpected = trade;
            }
        }
        int tradeExpectedID = tradeExpected.getId();

        tradeService.deleteById(tradeExpectedID);

        assertNull(tradeService.findById(tradeExpectedID).getAccount());
    }
}
