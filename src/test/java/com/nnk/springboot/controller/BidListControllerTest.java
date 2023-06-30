package com.nnk.springboot.controller;

import com.nnk.springboot.configuration.SpringSecurityConfig;
import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.BidListService;
import com.nnk.springboot.service.UserService;
import com.nnk.springboot.service.security.MyAuthenticationSuccessHandler;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;


import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.awt.*;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

//@WebMvcTest(BidListController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BidListControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private BidListService bidListService;
//
//    @MockBean
//    private SpringSecurityConfig springSecurityConfig;
//
//    @MockBean
//    private WebSecurityConfiguration webSecurityConfiguration;
//
//
//    @MockBean
//    private SecurityFilterChain springSecurityFilterChain;


//    @Autowired
//    private WebApplicationContext context;
//    @MockBean
//    private SecurityConfig securityConfig;
//    @MockBean
//    private WebSecurityConfiguration webSecurityConfiguration;
//    @MockBean
//    private UserService userService;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @MockBean
//    private BidListService bidListService;
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
//    @Before
//    public void setup() { mockMvc = MockMvcBuilders.webAppContextSetup(context) .apply(springSecurity()) .build(); }
//
//    @Test
//    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
//    public void shouldReturnBidListListView() throws Exception {
//
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/poseidon/bidList/list"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("bidList/list"))
//                .andExpect(MockMvcResultMatchers.model().attributeExists("listOfAllBids"));
//
//
//    }




    //////////////////////////////////////////////////////

    @Autowired
    private WebApplicationContext context;


    private MockMvc mvc;

    @Autowired
    private BidListService bidListService;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void shouldReturnBidListListView() throws Exception {

        this.mvc.perform(MockMvcRequestBuilders.get("/poseidon/bidList/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("bidList/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("listOfAllBids"));
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void shouldReturnBidFormView() throws Exception {

        this.mvc.perform(MockMvcRequestBuilders.get("/poseidon/bidList/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("bidList/add"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("bidList"));
    }

    @Test
    public void shouldValidateForm() throws Exception {

        BidList bidList = new BidList();
        bidList.setAccount("Account");
        bidList.setBidQuantity(10.0);
        bidList.setType("Type");

        this.mvc.perform(MockMvcRequestBuilders.post("/poseidon/bidList/validate", bidList)
                .with(SecurityMockMvcRequestPostProcessors.user("user1").roles("USER"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("bidList/add"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("bidList"));
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void shouldShowUpdateForm() throws Exception {

        BidList bidList = new BidList();
        bidList.setAccount("BidListController test");
        bidList.setBidQuantity(10.0);
        bidList.setType("Type");

        bidListService.saveBid(bidList);

        BidList bidExpected = new BidList();

        List<BidList> list = bidListService.getAllBids();

        for(BidList bid : list)
        {
            if(bid.getAccount().equals("BidListController test"))
            {
                bidExpected = bid;
            }
        }
        int bidExpectedID = bidExpected.getId();

        this.mvc.perform(MockMvcRequestBuilders.get("/poseidon/bidList/update/{id}", bidExpectedID))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("bidList/update"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("bidList"));

        bidListService.deleteById(bidExpectedID);
    }

    @Test
    public void shouldUpdateBid() throws Exception {

        BidList bidList = new BidList();
        bidList.setAccount("Account");
        bidList.setBidQuantity(10.0);
        bidList.setType("Type");

        this.mvc.perform(MockMvcRequestBuilders.post("/poseidon/bidList/update/{id}", 1)
                        .with(SecurityMockMvcRequestPostProcessors.user("user1").roles("USER"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("bidList/update"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("bidList"));
    }

    @Test
    public void shouldDeleteBid() throws Exception {

        BidList bidList = new BidList();
        bidList.setAccount("BidListController test");
        bidList.setBidQuantity(10.0);
        bidList.setType("Type");

        bidListService.saveBid(bidList);

        BidList bidExpected = new BidList();

        List<BidList> list = bidListService.getAllBids();

        for(BidList bid : list)
        {
            if(bid.getAccount().equals("BidListController test"))
            {
                bidExpected = bid;
            }
        }
        int bidExpectedID = bidExpected.getId();

        this.mvc.perform(MockMvcRequestBuilders.delete("/poseidon/bidList/delete/{id}", bidExpectedID)
                        .with(SecurityMockMvcRequestPostProcessors.user("user1").roles("USER"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/poseidon/bidList/list"));
    }

}
