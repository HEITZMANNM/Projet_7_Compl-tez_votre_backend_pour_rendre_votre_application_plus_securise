package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;

import java.util.List;

public interface BidListService {

    public List<BidList> getAllBids();

    public BidList saveBid(BidList bid);

    public BidList getBidById(int id);

    public void update(BidList bid);

    public void deleteById(int id);
}