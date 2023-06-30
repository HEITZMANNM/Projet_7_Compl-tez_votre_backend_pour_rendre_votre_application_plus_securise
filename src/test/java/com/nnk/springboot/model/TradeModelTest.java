package com.nnk.springboot.model;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TradeModelTest {

    @Test
    public void testHashCode()
    {
        Trade trade = new Trade();
        trade.setBuyQuantity(10);
        trade.setAccount("account");
        trade.setType("type");
        trade.setId(1);

        assertNotNull(trade.hashCode());

    }

    @Test
    public void testEquals()
    {
        Trade trade = new Trade();
        trade.setBuyQuantity(10);
        trade.setAccount("account");
        trade.setType("type");
        trade.setId(1);

        Trade trade2 = new Trade();
        trade2.setBuyQuantity(10);
        trade2.setAccount("account");
        trade2.setType("type");
        trade2.setId(1);

        Trade trade3 = new Trade();
        trade3.setBuyQuantity(10);
        trade3.setAccount("account trade3");
        trade3.setType("type");
        trade3.setId(1);


        assertTrue(trade.equals(trade));

        assertTrue(trade.equals(trade2));

        trade2 = null;

        assertFalse(trade.equals(trade2));

        assertFalse((new Trade().equals(trade)));

        assertFalse(trade.equals(trade3));

        trade3.setAccount("account");

        trade3.setType("type user3");

        assertFalse(trade.equals(trade3));
    }

    @Test
    public void testToString()
    {
        Trade trade3 = new Trade();
        trade3.setBuyQuantity(10);
        trade3.setAccount("account trade3");
        trade3.setType("type");
        trade3.setId(1);

        assertNotNull(trade3.toString());
    }
}
