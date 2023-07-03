package com.nnk.springboot.controller;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.BidListService;
import com.nnk.springboot.service.CurvePointService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CurveControllerTest {

    @Autowired
    private WebApplicationContext context;


    private MockMvc mvc;

    @Autowired
    private CurvePointService curvePointService;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void shouldReturnCurvePointListView() throws Exception {

        this.mvc.perform(MockMvcRequestBuilders.get("/poseidon/curvePoint/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("curvePoint/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("listOfAllCurvePoints"));
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void shouldReturnCurveFormView() throws Exception {

        this.mvc.perform(MockMvcRequestBuilders.get("/poseidon/curvePoint/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("curvePoint/add"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("curvePoint"));
    }

    @Test
    public void shouldValidateForm() throws Exception {

        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setTerm(11.0);
        curvePoint.setCurveId(0);
        curvePoint.setValue(11);


        this.mvc.perform(MockMvcRequestBuilders.post("/poseidon/curvePoint/validate", curvePoint)
                        .with(SecurityMockMvcRequestPostProcessors.user("user1").roles("USER"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/poseidon/curvePoint/list"));

        CurvePoint curvePointExpected = new CurvePoint();

        List<CurvePoint> list = curvePointService.getAllCurvePoints();

        for(CurvePoint curve : list)
        {
            if(curve.getCurveId() == 0)
            {
                curvePointExpected = curve;
            }
        }
        int curvePointID = curvePointExpected.getId();

        curvePointService.deleteById(curvePointID);
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void shouldShowUpdateForm() throws Exception {

        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setTerm(0.0);
        curvePoint.setCurveId(0);
        curvePoint.setValue(11);


        curvePointService.saveCurvePoint(curvePoint);

        CurvePoint curvePointExpected = new CurvePoint();

        List<CurvePoint> list = curvePointService.getAllCurvePoints();

        for(CurvePoint curve : list)
        {
            if(curve.getCurveId() == 0)
            {
                curvePointExpected = curve;
            }
        }
        int curvePointID = curvePointExpected.getId();

        this.mvc.perform(MockMvcRequestBuilders.get("/poseidon/curvePoint/update/{id}", curvePointID))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("curvePoint/update"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("curvePoint"));

        curvePointService.deleteById(curvePointID);
    }

    @Test
    public void shouldUpdateCurve() throws Exception {

        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setTerm(0.0);
        curvePoint.setCurveId(0);
        curvePoint.setValue(11);

        curvePointService.saveCurvePoint(curvePoint);

        this.mvc.perform(MockMvcRequestBuilders.post("/poseidon/curvePoint/update/{id}", 1)
                        .with(SecurityMockMvcRequestPostProcessors.user("user1").roles("USER"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/poseidon/curvePoint/list"));

        CurvePoint curvePointExpected = new CurvePoint();

        List<CurvePoint> list = curvePointService.getAllCurvePoints();

        for(CurvePoint curve : list)
        {
            if(curve.getCurveId() == 0)
            {
                curvePointExpected = curve;
            }
        }
        int curveExpectedID = curvePointExpected.getId();

        curvePointService.deleteById(curveExpectedID);
    }



    @Test
    public void shouldDeleteCurve() throws Exception {

        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setTerm(0.0);
        curvePoint.setCurveId(0);
        curvePoint.setValue(11);

        curvePointService.saveCurvePoint(curvePoint);

        CurvePoint curvePointExpected = new CurvePoint();

        List<CurvePoint> list = curvePointService.getAllCurvePoints();

        for(CurvePoint curve : list)
        {
            if(curve.getCurveId() == 0)
            {
                curvePointExpected = curve;
            }
        }
        int curveExpectedID = curvePointExpected.getId();

        this.mvc.perform(MockMvcRequestBuilders.get("/poseidon/curvePoint/delete/{id}", curveExpectedID)
                        .with(SecurityMockMvcRequestPostProcessors.user("user1").roles("USER"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/poseidon/curvePoint/list"));
    }
}
