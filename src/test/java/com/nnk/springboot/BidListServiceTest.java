package com.nnk.springboot;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@SpringBootTest
public class BidListServiceTest {

    @MockBean
    private BidListRepository bidListRepository;

    @Autowired
    private BidListService bidListService;

    private BidList bidListTest;
    @BeforeEach
    public void setUp()
    {
        bidListTest = new BidList();
        bidListTest.setAccount("Account");
        bidListTest.setBidQuantity(10.0);
        bidListTest.setType("Type");

        List<BidList> bidListFind = new ArrayList<>();
        bidListFind.add(bidListTest);

        when(bidListRepository.findAll()).thenReturn(bidListFind);
        when(bidListRepository.findById(anyInt())).thenReturn(bidListTest);
    }

    @Test
    public void testToGetAllBids()
    {
        List<BidList> bidListExpected = bidListService.getAllBids();

        assertEquals(bidListExpected.get(0).getAccount(), "Account");
    }

    @Test
    public void TestToGetBidById()
    {
        BidList bidListExpected = bidListService.getBidById(1);

        assertEquals(bidListExpected.getType(), "Type");
    }
}
