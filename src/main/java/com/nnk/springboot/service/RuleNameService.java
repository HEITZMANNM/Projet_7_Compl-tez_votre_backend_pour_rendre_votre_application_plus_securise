package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;

import java.util.List;

public interface RuleNameService {
   public List<RuleName> findAll();
   public void save(RuleName ruleName);

  public RuleName findById(Integer id);

   public void update(RuleName ruleName);

    public void deleteById(Integer id);
}
