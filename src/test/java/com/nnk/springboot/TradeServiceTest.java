package com.nnk.springboot;


import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.TradeService;

import org.junit.jupiter.api.AfterEach;
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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
public class TradeServiceTest {


    @MockBean
    private TradeRepository tradeRepository;

    @Autowired
    private TradeService tradeService;

    private Trade tradeTest;

    @BeforeEach
    public void setUp()
    {
        tradeTest = new Trade();
        tradeTest.setBuyQuantity(10);
        tradeTest.setType("Type test");
        tradeTest.setAccount("Account Test");

        List<Trade> tradeList = new ArrayList<>();
        tradeList.add(tradeTest);

        when(tradeRepository.findAll()).thenReturn(tradeList);
        when(tradeRepository.findById(any())).thenReturn(Optional.ofNullable(tradeTest));
    }




    @Test
    public void testToGetAllTrades()
    {
        List<Trade> list = tradeService.findAll();

        assertEquals(list.get(0).getType(), "Type test");
    }

    @Test
    public void testToGetTradeById()
    {
        Trade tradeExpected = tradeService.findById(1);

        assertEquals(tradeExpected.getAccount(), "Account Test");
    }




}
