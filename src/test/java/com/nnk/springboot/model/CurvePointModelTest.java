package com.nnk.springboot.model;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CurvePointModelTest {

    @Test
    public void testHashCode()
    {
        CurvePoint curve = new CurvePoint();
        curve.setCurveId(1);
        curve.setValue(10);
        curve.setTerm(10);
        curve.setCurveId(2);

        assertNotNull(curve.hashCode());

    }

    @Test
    public void testEquals()
    {
        CurvePoint curve = new CurvePoint();
        curve.setCurveId(1);
        curve.setValue(10);
        curve.setTerm(10);
        curve.setCurveId(2);

        CurvePoint curve2 = new CurvePoint();
        curve2.setCurveId(1);
        curve2.setValue(10);
        curve2.setTerm(10);
        curve2.setCurveId(2);

        CurvePoint curve3 = new CurvePoint();
        curve3.setCurveId(1);
        curve3.setValue(10);
        curve3.setTerm(10);
        curve3.setCurveId(4);


        assertTrue(curve.equals(curve));

        assertTrue(curve.equals(curve2));

        curve2 = null;

        assertFalse(curve.equals(curve2));

        assertFalse((new CurvePoint().equals(curve)));

        assertFalse(curve.equals(curve3));

        curve3.setCurveId(2);

        curve3.setTerm(11);

        assertFalse(curve.equals(curve3));
    }

    @Test
    public void testToString()
    {
        CurvePoint curve3 = new CurvePoint();
        curve3.setCurveId(1);
        curve3.setValue(10);
        curve3.setTerm(10);
        curve3.setCurveId(4);
        assertNotNull(curve3.toString());
    }
}
