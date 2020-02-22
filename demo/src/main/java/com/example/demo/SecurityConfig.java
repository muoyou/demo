package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        //配置不需要登陆验证
         http.authorizeRequests().anyRequest().permitAll().and().logout().permitAll().and().csrf().disable().headers().frameOptions().disable();
       /* http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/")
                .and()
                .authorizeRequests()
                .antMatchers("/login.html", "/", "/error")
                .permitAll()
                .anyRequest().authenticated();*/

    }

}
