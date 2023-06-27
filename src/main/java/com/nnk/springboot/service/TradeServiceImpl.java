package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.repositories.TradeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TradeServiceImpl implements TradeService{

    private static final Logger logger = LogManager.getLogger("TradeService");

    @Autowired
    private TradeRepository tradeRepository;


    @Override
    public List<Trade> findAll() {

        List<Trade> listOfAllTrade = new ArrayList<>();
        try {
            listOfAllTrade = tradeRepository.findAll();

            logger.debug("The Trades were find");
        }
        catch(Exception ex){
            logger.error("Error fetching the list of trades", ex);
        }
        return listOfAllTrade;
    }
}
