package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;

import java.util.List;

public interface CurvePointService {

   public List<CurvePoint> getAllCurvePoints();

    public void saveCurvePoint(CurvePoint curvePoint);

    public CurvePoint getCurvePointById(Integer id);

   public void update(CurvePoint curvePoint);

   public void deleteById(int id);
}
