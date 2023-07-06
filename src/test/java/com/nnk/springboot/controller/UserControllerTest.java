package com.nnk.springboot.controller;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
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
public class UserControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "ADMIN")
    public void shouldReturnUserListView() throws Exception {

        this.mvc.perform(MockMvcRequestBuilders.get("/poseidon/user/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("users"));
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "ADMIN")
    public void shouldReturnUserFormView() throws Exception {

        this.mvc.perform(MockMvcRequestBuilders.get("/poseidon/user/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/add"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"));
    }

    @Test
    public void shouldValidateForm() throws Exception {

        this.mvc.perform(MockMvcRequestBuilders.post("/poseidon/user/validate")
                        .param("fullname", "fullNameControllerTest")
                        .param("password", "passwordControllerTest2!")
                        .param("role", "USER")
                        .param("username", "userNameControllerTest")
                        .with(SecurityMockMvcRequestPostProcessors.user("user1").roles("ADMIN"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/poseidon/user/list"));

        User userExpected = new User();

        List<User> list = userRepository.findAll();

        for(User user : list)
        {
            if(user.getFullname().equals("fullNameControllerTest"))
            {
                userExpected = user;
            }
        }
        int userId = userExpected.getId();

        userRepository.deleteById(userId);
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "ADMIN")
    public void shouldShowUpdateForm() throws Exception {

        User user = new User();
        user.setUsername("userNameControllerTest");
        user.setPassword("passwordControllerTest2!");
        user.setRole("USER");
        user.setFullname("fullNameControllerTest");

        userRepository.save(user);

        User userExpected = new User();

        List<User> list = userRepository.findAll();

        for(User userFind : list)
        {
            if("fullNameControllerTest".equals(userFind.getFullname()))
            {
                userExpected = userFind;
            }
        }
        int userId = userExpected.getId();

        this.mvc.perform(MockMvcRequestBuilders.get("/poseidon/user/update/{id}", userId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/update"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"));

        userRepository.deleteById(userId);
    }

    @Test
    public void shouldUpdateUser() throws Exception {

        User user = new User();
        user.setUsername("userNameControllerTest");
        user.setPassword("passwordControllerTest2!");
        user.setRole("USER");
        user.setFullname("fullNameControllerTest");

        userRepository.save(user);

        User userExpected = new User();

        List<User> list = userRepository.findAll();

        for(User userFind : list)
        {
            if("fullNameControllerTest".equals(userFind.getFullname()))
            {
                userExpected = userFind;
            }
        }
        int userId = userExpected.getId();


        this.mvc.perform(MockMvcRequestBuilders.post("/poseidon/user/update/{id}", userId)
                        .param("fullname", "fullNameControllerUpdated")
                        .param("password", "passwordControllerTest2!")
                        .param("role", "USER")
                        .param("username", "userNameControllerTest")
                        .with(SecurityMockMvcRequestPostProcessors.user("user1").roles("ADMIN"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/poseidon/user/list"));

        userRepository.deleteById(userId);
    }

    @Test
    public void shouldDeleteUser() throws Exception {


        User user = new User();
        user.setUsername("userNameControllerTest");
        user.setPassword("passwordControllerTest2!");
        user.setRole("USER");
        user.setFullname("fullNameControllerTest");

        userRepository.save(user);

        User userExpected = new User();

        List<User> list = userRepository.findAll();

        for(User userFind : list)
        {
            if("fullNameControllerTest".equals(userFind.getFullname()))
            {
                userExpected = userFind;
            }
        }
        int userId = userExpected.getId();

        this.mvc.perform(MockMvcRequestBuilders.get("/poseidon/user/delete/{id}", userId)
                        .with(SecurityMockMvcRequestPostProcessors.user("user1").roles("ADMIN"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/poseidon/user/list"));
    }


}
