package com.nnk.springboot.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests().antMatchers(
                        "/poseidon/")
                .permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/poseidon/login");

    }
}