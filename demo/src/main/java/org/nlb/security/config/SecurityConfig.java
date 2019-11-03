package org.nlb.security.config;

import org.nlb.security.utils.picture.StudentFilter;
import org.nlb.security.utils.returnLogin.FailureLogin;
import org.nlb.security.utils.returnLogin.SuccessLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
    @Autowired
    StringRedisTemplate redisTemplate;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        StudentFilter filter = new StudentFilter();
        filter.setFailureLogin(failureLogin);
        filter.setRedisTemplate(redisTemplate);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
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
