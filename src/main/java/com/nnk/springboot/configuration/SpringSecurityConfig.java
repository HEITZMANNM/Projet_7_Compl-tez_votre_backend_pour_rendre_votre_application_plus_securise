package com.nnk.springboot.configuration;

import com.nnk.springboot.service.security.AuthenticationService;

import com.nnk.springboot.service.security.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    public SpringSecurityConfig(MyAuthenticationSuccessHandler myAuthenticationSuccessHandler)
    {
        this.myAuthenticationSuccessHandler=myAuthenticationSuccessHandler;

    }

//    @Autowired
//    private AuthenticationService authenticationService;


    @Bean
    public  AuthenticationService authentication() {

        return new AuthenticationService();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(authentication()).passwordEncoder(passwordEncoder());
    }



    @Override
    public void configure(HttpSecurity http) throws Exception
    {

//        http.authorizeRequests().antMatchers(
//                        "/js/**",
//                        "/css/**",
//                        "/img/**",
//                        "/poseidon/")
//                .permitAll()
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/poseidon/login");

        String[] staticResources  =  {
                "/css/**",
                "/images/**",
                "/img/**",
                "/fonts/**",
                "/scripts/**",
                "/../../logoPoseidon.png"
        };

        http.authorizeRequests().antMatchers(staticResources).permitAll()
//                .antMatchers("/poseidon/user/add").hasAuthority("ROLE_ADMIN")
//                .antMatchers("/poseidon/user/list").hasAuthority("ROLE_ADMIN")
//                .antMatchers("/poseidon/user/update").hasAuthority("ROLE_ADMIN")
                .antMatchers("/poseidon/user/add").hasRole("ADMIN")
                .antMatchers("/poseidon/user/list").hasRole("ADMIN")
                .antMatchers("/poseidon/user/update").hasRole("ADMIN")
//                .antMatchers("/poseidon/user/add").hasAnyRole("ADMIN")
//                .antMatchers("/poseidon/user/list").hasAnyRole("ADMIN")
//                .antMatchers("/poseidon/user/update").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/poseidon/login")
                .successHandler(myAuthenticationSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/poseidon/login")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/poseidon/logout"))
                .permitAll();

    }


}
