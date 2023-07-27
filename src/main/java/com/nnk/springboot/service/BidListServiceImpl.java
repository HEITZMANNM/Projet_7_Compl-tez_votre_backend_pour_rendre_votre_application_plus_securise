package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BidListServiceImpl implements BidListService{



    @Autowired
    private BidListRepository bidListRepository;

    private static final Logger logger = LogManager.getLogger("BidListService");

    @Override
    public List<BidList> getAllBids()
    {
        List<BidList> listOfAllBids = new ArrayList<>();
        try {
            listOfAllBids = bidListRepository.findAll();

            log.info("The Bids were find");
        }
        catch(Exception ex){
            logger.error("Error fetching the list of bids", ex);
        }
        return listOfAllBids;
    }

    @Override
    public BidList saveBid(BidList bid)
    {
        BidList bideToSaved = bid;
        try {
            bidListRepository.save(bideToSaved);
            logger.debug("The bid was saved");

        }
        catch(Exception ex){
            logger.error("Error to save the bid", ex);
        }

        return bideToSaved;
    }
    @Override
    public BidList getBidById(int id)
    {
        BidList bid= null;
        try {
            bid = bidListRepository.findById(id);
            logger.debug("The bid was find");

        }
        catch(Exception ex){
            logger.error("Error fetching the bid", ex);
        }
        return bid;
    }

    @Override
    public void update(BidList bid)
    {
        try {

            BidList bidSaved = bidListRepository.findById(bid.getId());

            bidSaved.setBidQuantity(bid.getBidQuantity());
            bidSaved.setType(bid.getType());
            bidSaved.setAccount(bid.getAccount());

            bidListRepository.save(bidSaved);
            logger.debug("The bid was updated");
        }
        catch (Exception ex)
        {
            logger.error("Error update the bid", ex);
        }
    }

    @Override
    public void deleteById(int id)
    {
        try{
            bidListRepository.deleteById(id);
            logger.debug("The bid was deleted");
        }
        catch (Exception ex)
        {
            logger.error("Error delete the bid", ex);
        }
    }
}