package com.nnk.springboot.integration;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;
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
public class BidListServiceIT {


    @Autowired
    BidListService bidListService;

    @Autowired
    BidListRepository bidListRepository;

    private BidList bidListTest;

    @AfterEach
    public void cleanDataBase()
    {
        List<BidList> list = bidListService.getAllBids();

        for(BidList bid : list)
        {
            if(bid.getAccount().equals("AccountTest"))
            {
                bidListService.deleteById(bid.getId());
            }
        }
    }

    @BeforeEach
    public void setUp()
    {
        bidListTest = new BidList();
        bidListTest.setAccount("AccountTest");
        bidListTest.setBidQuantity(10.0);
        bidListTest.setType("TypeTest");

        bidListService.saveBid(bidListTest);
    }

    @Test
    public void testToSaveABidList()
    {
        BidList bidExpected = new BidList();

        List<BidList> list = bidListService.getAllBids();

        for(BidList bid : list)
        {
            if(bid.getAccount().equals("AccountTest"))
            {
                bidExpected = bid;
            }
        }

        assertEquals(bidExpected.getType(), "TypeTest");
        assertEquals(bidExpected.getBidQuantity(), 10.0);
    }

    @Test
    public void TestToUpdate()
    {
        BidList bidExpected = new BidList();

        List<BidList> list = bidListService.getAllBids();

        for(BidList bid : list)
        {
            if(bid.getAccount().equals("AccountTest"))
            {
                bidExpected = bid;
            }
        }

        int bidExpectedID = bidExpected.getId();

        BidList bidUpDated = new BidList();
        bidUpDated.setAccount("AccountTest");
        bidUpDated.setBidQuantity(20.0);
        bidUpDated.setType("TypeTest");
        bidUpDated.setId(bidExpectedID);

        bidListService.update(bidUpDated);

        assertEquals(bidListService.getBidById(bidExpectedID).getBidQuantity(), 20.0);
    }

    @Test
    public void testToDeleteById()
    {
        BidList bidExpected = new BidList();

        List<BidList> list = bidListService.getAllBids();

        for(BidList bid : list)
        {
            if(bid.getAccount().equals("AccountTest"))
            {
                bidExpected = bid;
            }
        }
        int bidExpectedID = bidExpected.getId();

        bidListService.deleteById(bidExpectedID);

        assertNull(bidListService.getBidById(bidExpectedID));
    }
}
