package com.example.simpleapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static com.example.simpleapp.Constants.ELOQUA_AUTHORIZATION_CODE_ENDPOINT;
import static com.example.simpleapp.Constants.ELOQUA_CONFIGURE_ENDPOINT;
import static com.example.simpleapp.Constants.ELOQUA_INSTALL_ENDPOINT;
import static com.example.simpleapp.Constants.ELOQUA_STATUS_ENDPOINT;
import static com.example.simpleapp.Constants.ELOQUA_UNINSTALL_ENDPOINT;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login*", ELOQUA_AUTHORIZATION_CODE_ENDPOINT)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(ELOQUA_INSTALL_ENDPOINT, ELOQUA_UNINSTALL_ENDPOINT, ELOQUA_STATUS_ENDPOINT, "/all");
    }
}
