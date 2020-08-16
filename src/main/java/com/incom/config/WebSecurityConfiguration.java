package com.incom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.incom.security.FirebaseAuthenticationFilter;
import com.incom.security.Role;

/**
 * セキュリティ設定クラス。
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private FirebaseAuthenticationFilter firebaseAuthenticationFilter;

    @Override
    public void configure(WebSecurity web) throws Exception {

        // 静的リソースに対するアクセスはセキュリティ設定を無視
        web.ignoring().antMatchers(
                "/images/**",
                "/css/**",
                "/javascript/**",
                "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests().antMatchers("/api/open/**").hasAnyRole(Role.USER, Role.ADMIN);
        httpSecurity.authorizeRequests().antMatchers("/api/client/**").hasRole(Role.USER);
        httpSecurity.authorizeRequests().antMatchers("/api/admin/**").hasAnyRole(Role.ADMIN);
        httpSecurity.authorizeRequests().antMatchers("/health/**").hasAnyRole(Role.ADMIN);
        httpSecurity.authorizeRequests().antMatchers("/**").permitAll();
        httpSecurity.authorizeRequests().and().csrf().disable();

        // Firebase認証フィルタを登録
        httpSecurity.addFilterBefore(firebaseAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
