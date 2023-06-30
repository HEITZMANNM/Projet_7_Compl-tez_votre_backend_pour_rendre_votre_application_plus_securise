package com.nnk.springboot.model;

import com.nnk.springboot.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserModelTest {

    @Test
    public void testHashCode()
    {
        User user = new User();
        user.setPassword("password");
        user.setFullname("fullname");
        user.setRole("USER");
        user.setUsername("username");
        user.setId(0);

        assertNotNull(user.hashCode());

    }

    @Test
    public void testEquals()
    {
        User user = new User();
        user.setPassword("password");
        user.setFullname("fullname");
        user.setRole("USER");
        user.setUsername("username");
        user.setId(0);

        User user2 = new User();
        user2.setPassword("password");
        user2.setFullname("fullname");
        user2.setRole("USER");
        user2.setUsername("username");
        user2.setId(0);

        User user3 = new User();
        user3.setPassword("password user3");
        user3.setFullname("fullname");
        user3.setRole("USER");
        user3.setUsername("username");
        user3.setId(0);


        assertTrue(user.equals(user));

        assertTrue(user.equals(user2));

        user2 = null;

        assertFalse(user.equals(user2));

        assertFalse((new User().equals(user)));

        assertFalse(user.equals(user3));

        user3.setPassword("password");

        user3.setFullname("fullname user3");

        assertFalse(user.equals(user3));
    }

    @Test
    public void testToString()
    {
        User user3 = new User();
        user3.setPassword("password user3");
        user3.setFullname("fullname");
        user3.setRole("USER");
        user3.setUsername("username");
        user3.setId(0);

        assertNotNull(user3.toString());
    }
}
