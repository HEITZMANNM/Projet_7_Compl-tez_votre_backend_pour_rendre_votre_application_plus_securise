package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;

import java.util.List;

public interface TradeService {
    public List<Trade> findAll();

    public void save(Trade trade);

    public Trade findById(Integer id);

    public void upDate(Trade trade);

    public void deleteById(Integer id);
}
