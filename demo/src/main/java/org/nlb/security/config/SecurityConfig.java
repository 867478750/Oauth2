package org.nlb.security.config;

import org.nlb.security.utils.returnLogin.FailureLogin;
import org.nlb.security.utils.returnLogin.SuccessLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
       // web.ignoring().antMatchers("/**");
    }

    @Autowired
    SuccessLogin successLogin;
    @Autowired
    FailureLogin failureLogin;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/security/judge")
                .loginProcessingUrl("/student/login")
              //  .defaultSuccessUrl("/student/success")
                .successHandler(successLogin)
                .failureHandler(failureLogin)
                .and()
                .authorizeRequests()
                .antMatchers("/security/judge","/student/login", "/login.html","/login/image").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
