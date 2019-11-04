package org.nlb.security.config;

import org.nlb.security.returnLogin.FailureLogin;
import org.nlb.security.returnLogin.SuccessLogin;
import org.nlb.security.utils.StudentServiceUtils;
import org.nlb.security.utils.picture.StudentFilter;
import org.nlb.security.utils.session.SessionSty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

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
    @Autowired
    StudentServiceUtils studentServiceUtils;

    @Autowired
    LogoutSuccessHandler logoutSuccessHandler;



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
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(6000)
                .userDetailsService(studentServiceUtils)
                .and()
                .sessionManagement()
                .invalidSessionUrl("/student/sessionInvalidate")
                .maximumSessions(1)
                .expiredSessionStrategy(new SessionSty())
                .and()
                .and()
                .logout()
                .logoutUrl("/student/logout")
               // .logoutSuccessUrl("/login.html")
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .and()
                .authorizeRequests()
                .antMatchers("/security/judge","/student/login", "/login.html","/login/image","/student/sessionInvalidate","/student/logout").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }

    @Autowired
    DataSource dataSource;
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }


}
