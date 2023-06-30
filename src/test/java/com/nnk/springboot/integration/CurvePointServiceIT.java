package com.nnk.springboot.integration;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.BidListService;
import com.nnk.springboot.service.CurvePointService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class CurvePointServiceIT {

    @Autowired
    CurvePointService curvePointService;

    @Autowired
    CurvePointRepository curvePointRepository;

    private CurvePoint curvePointTest;

    @BeforeEach
    public void setUp()
    {
        curvePointTest = new CurvePoint();
        curvePointTest.setTerm(10);
        curvePointTest.setCurveId(77);
        curvePointTest.setValue(10);

        curvePointService.saveCurvePoint(curvePointTest);
    }

    @AfterEach
    public void cleanDataBase()
    {
        List<CurvePoint> list = curvePointService.getAllCurvePoints();

        for(CurvePoint curve : list)
        {
            if(curve.getCurveId() == 77)
            {
                curvePointService.deleteById(curve.getId());
            }
        }
    }

    @Test
    public void testToSaveCurvePoint()
    {
        CurvePoint curvePointExpected = new CurvePoint();

        List<CurvePoint> list = curvePointService.getAllCurvePoints();

        for(CurvePoint curve : list)
        {
            if(curve.getCurveId()==77)
            {
                curvePointExpected = curve;
            }
        }

        assertEquals(curvePointExpected.getTerm(), 10);
    }

    @Test
    public void testToGetBidById()
    {
        CurvePoint curveSaved = new CurvePoint();

        List<CurvePoint> list = curvePointService.getAllCurvePoints();

        for(CurvePoint curve : list)
        {
            if(curve.getCurveId()==77)
            {
                curveSaved = curve;
            }
        }

        CurvePoint curveExpected =  curvePointService.getCurvePointById(curveSaved.getId());

        assertEquals(curveExpected.getCurveId(), 77);
    }

    @Test
    public void testToUpdate()
    {
        CurvePoint curvePointExpected = new CurvePoint();

        List<CurvePoint> list = curvePointService.getAllCurvePoints();

        for(CurvePoint curve : list)
        {
            if(curve.getCurveId() == 77 )
            {
                curvePointExpected = curve;
            }
        }

        int indexCurvePointSaved = curvePointExpected.getId();

        CurvePoint curvePointUpDate = new CurvePoint();
        curvePointUpDate.setTerm(10);
        curvePointUpDate.setValue(20);
        curvePointUpDate.setCurveId(77);
        curvePointUpDate.setId(indexCurvePointSaved);

        curvePointService.update(curvePointUpDate);

        assertEquals(curvePointService.getCurvePointById(indexCurvePointSaved).getValue(), 20);
    }
    @Test
    public void testToDeleteById()
    {
        CurvePoint curveSaved = new CurvePoint();

        List<CurvePoint> list = curvePointService.getAllCurvePoints();

        for(CurvePoint curve : list)
        {
            if(curve.getCurveId()==77)
            {
                curveSaved = curve;
            }
        }

        int idCurveSaved = curveSaved.getId();

        curvePointService.deleteById(idCurveSaved);

        CurvePoint curveExpected =  curvePointService.getCurvePointById(curveSaved.getId());

        assertEquals(curveExpected.getCurveId(), 0);
    }
}
