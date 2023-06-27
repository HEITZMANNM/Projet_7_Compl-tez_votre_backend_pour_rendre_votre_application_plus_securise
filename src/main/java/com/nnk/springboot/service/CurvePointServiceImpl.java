package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurvePointServiceImpl implements CurvePointService{

    @Autowired
    private CurvePointRepository curvePointRepository;

    private static final Logger logger = LogManager.getLogger("CurvePointService");

    @Override
    public List<CurvePoint> getAllCurvePoints()
    {
        List<CurvePoint> listOfAllCurvePoints = new ArrayList<>();

        try
        {
            listOfAllCurvePoints = curvePointRepository.findAll();
            logger.debug("The Curve points were find");
        }
        catch(Exception ex){
            logger.error("Error fetching the list of curve points", ex);
        }
        return listOfAllCurvePoints;
    }

    @Override
    public void saveCurvePoint(CurvePoint curvePoint)
    {
        try
        {
            curvePointRepository.save(curvePoint);
            logger.debug("The Curve point was saved");
        }
        catch(Exception ex){
            logger.error("Error saving the curve point", ex);
        }
    }

    @Override
    public CurvePoint getCurvePointById(Integer id)
    {
        CurvePoint curvePoint =  new CurvePoint();
        try
        {
            curvePoint = curvePointRepository.findById(id).get();
            logger.debug("The Curve point was find");
        }
        catch(Exception ex){
            logger.error("Error fetching the curve point", ex);
        }

        return curvePoint;
    }

    @Override
    public void update(CurvePoint curvePoint)
    {
        try {
            CurvePoint curvePointSaved = curvePointRepository.findById(curvePoint.getId());

            curvePointSaved.setCurveId(curvePoint.getCurveId());
            curvePointSaved.setTerm(curvePoint.getTerm());
            curvePointSaved.setValue(curvePoint.getValue());

            curvePointRepository.save(curvePointSaved);
            logger.debug("The curve point was updated");
        }
        catch (Exception ex)
        {
            logger.error("Error update the curve point", ex);
        }
    }

    @Override
    public void deleteById(int id)
    {
        try{
            curvePointRepository.deleteById(id);
            logger.debug("The curvePoint was deleted");
        }
        catch (Exception ex)
        {
            logger.error("Error delete the curvePoint", ex);
        }
    }


}
