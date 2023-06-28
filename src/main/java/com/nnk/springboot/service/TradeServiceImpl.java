package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.RuleName;
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

    @Override
    public void save(Trade trade) {

        try {
            tradeRepository.save(trade);

            logger.debug("The Trade was saved");
        }
        catch(Exception ex){
            logger.error("Error saving the trade", ex);
        }

    }

    @Override
    public Trade findById(Integer id) {

        Trade trade = new Trade();

        try {
            trade = tradeRepository.findById(id).get();

            logger.debug("The Trade was find");
        }
        catch(Exception ex){
            logger.error("Error fetching the trade", ex);
        }
        return trade;
    }

    @Override
    public void upDate(Trade trade) {

        try
        {
           Trade tradeSaved = tradeRepository.findById(trade.getId()).get();

           tradeSaved.setAccount(trade.getAccount());
           tradeSaved.setType(trade.getType());
           tradeSaved.setBuyQuantity(trade.getBuyQuantity());

           tradeRepository.save(tradeSaved);

            logger.debug("The trade was updated");
        }
        catch(Exception ex){
            logger.error("Error to upDate the trade", ex);
        }

    }

    @Override
    public void deleteById(Integer id) {

        try {
            tradeRepository.deleteById(id);

            logger.debug("The Trade was deleted");
        }
        catch(Exception ex){
            logger.error("Error to delete the trade", ex);
        }

    }
}
