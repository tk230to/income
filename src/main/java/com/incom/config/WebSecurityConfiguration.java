package com.incom.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()//
                .antMatchers("/api/open/**").hasAnyRole(Roles.ANONYMOUS)//
                .antMatchers("/api/client/**").hasRole(Roles.USER)//
                .antMatchers("/api/admin/**").hasAnyRole(Roles.ADMIN)//
                .antMatchers("/health/**").hasAnyRole(Roles.ADMIN)//
                .antMatchers("/**").permitAll()//
                .and().csrf().disable()//
                .anonymous().authorities(Roles.ROLE_ANONYMOUS);//
    }
}
