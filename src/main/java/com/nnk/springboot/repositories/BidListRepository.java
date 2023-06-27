package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BidListRepository extends JpaRepository<BidList, Integer> {

    public List<BidList> findAll();

    public BidList save(BidList bidList);
    public BidList findById(int id);
    public void deleteById(int id);
}