package com.nnk.springboot.controller;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
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
public class BidListControllerTest {


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


        this.mvc.perform(MockMvcRequestBuilders.post("/poseidon/bidList/validate").param("account", "AccountControllerTest")
                        .param("bidQuantity", "10.0")
                        .param("type", "Type")
                        .with(SecurityMockMvcRequestPostProcessors.user("user1").roles("USER"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/poseidon/bidList/list"));

        BidList bidExpected = new BidList();

        List<BidList> list = bidListService.getAllBids();

        for(BidList bid : list)
        {
            if(bid.getAccount().equals("AccountControllerTest"))
            {
                bidExpected = bid;
            }
        }
        int bidExpectedID = bidExpected.getId();

        bidListService.deleteById(bidExpectedID);
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
        bidList.setAccount("AccountController test");
        bidList.setBidQuantity(10.0);
        bidList.setType("Type");

        bidListService.saveBid(bidList);

        bidList.setAccount("AccountControllerUpdated");

        BidList bidExpected = new BidList();

        List<BidList> list = bidListService.getAllBids();

        for(BidList bid : list)
        {
            if(bid.getAccount().equals("AccountController test"))
            {
                bidExpected = bid;
            }
        }
        int bidExpectedID = bidExpected.getId();

        this.mvc.perform(MockMvcRequestBuilders.post("/poseidon/bidList/update/{id}", String.valueOf(bidExpectedID))
                        .param("account", "AccountControllerUpdated")
                        .param("bidQuantity", "10.0")
                        .param("type", "Type")
                        .with(SecurityMockMvcRequestPostProcessors.user("user1").roles("USER"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/poseidon/bidList/list"));



        bidListService.deleteById(bidExpectedID);
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
