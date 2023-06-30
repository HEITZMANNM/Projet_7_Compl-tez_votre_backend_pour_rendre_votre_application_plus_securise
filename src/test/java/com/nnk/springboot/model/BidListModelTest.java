package com.nnk.springboot.model;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BidListModelTest {

    @Test
    public void testHashCode()
    {
        BidList bid = new BidList();
        bid.setId(1);
        bid.setBidQuantity(10.0);
        bid.setType("type");
        bid.setAccount("account");


        assertNotNull(bid.hashCode());

    }

    @Test
    public void testEquals()
    {
        BidList bid = new BidList();
        bid.setId(1);
        bid.setBidQuantity(10.0);
        bid.setType("type");
        bid.setAccount("account");

        BidList bid2 = new BidList();
        bid2.setId(1);
        bid2.setBidQuantity(10.0);
        bid2.setType("type");
        bid2.setAccount("account");

        BidList bid3 = new BidList();
        bid3.setId(1);
        bid3.setBidQuantity(20.0);
        bid3.setType("type");
        bid3.setAccount("account");


        assertTrue(bid.equals(bid));

        assertTrue(bid.equals(bid2));

        bid2 = null;

        assertFalse(bid.equals(bid2));

        assertFalse((new BidList().equals(bid)));

        assertFalse(bid.equals(bid3));

        bid3.setBidQuantity(10.0);

        bid3.setType("type bid3");

        assertFalse(bid.equals(bid3));
    }

    @Test
    public void testToString()
    {
        BidList bid3 = new BidList();
        bid3.setId(1);
        bid3.setBidQuantity(20.0);
        bid3.setType("type");
        bid3.setAccount("account");

        assertNotNull(bid3.toString());
    }
}
