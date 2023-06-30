package com.nnk.springboot;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.BidListService;
import com.nnk.springboot.service.CurvePointService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@SpringBootTest
public class CurvePointServiceTest {
    @MockBean
    private CurvePointRepository curvePointRepository;

    @Autowired
    private CurvePointService curvePointService;

    private CurvePoint curvePointTest;

    @BeforeEach
    public void setUp()
    {
        curvePointTest = new CurvePoint();
        curvePointTest.setTerm(10.0);
        curvePointTest.setValue(10.0);
        curvePointTest.setCurveId(777);

        List<CurvePoint> curvePointList = new ArrayList<>();
        curvePointList.add(curvePointTest);

        when(curvePointRepository.findAll()).thenReturn(curvePointList);
    }

    @Test
    public void testToGetAllCurvePoint()
    {
        List<CurvePoint> curvePointListExpected = curvePointService.getAllCurvePoints();

        assertEquals(curvePointListExpected.get(0).getCurveId(), 777);
    }
}
