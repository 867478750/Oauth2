package org.nlb.security.utils.picture;

import org.apache.commons.lang.StringUtils;
import org.nlb.security.returnLogin.FailureLogin;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Configuration
public class StudentFilter extends OncePerRequestFilter {

    private StringRedisTemplate redisTemplate;
    private FailureLogin failureLogin;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(StringUtils.equals("/student/login",request.getRequestURI())&&StringUtils.equalsIgnoreCase("POST",request.getMethod())){
            try {
                String remoteAddr = request.getRemoteAddr();
                String code = redisTemplate.opsForValue().get("code"+remoteAddr);
                validate(new ServletWebRequest(request),code);
            }catch (StudentPictureException e){
                failureLogin.onAuthenticationFailure(request,response,e);
            }
        }
        filterChain.doFilter(request, response);
    }
    private void validate(ServletWebRequest request,String code) throws ServletRequestBindingException {
        String code1 = ServletRequestUtils.getStringParameter(request.getRequest(), "code");
        if(!code.equals(code1)){
            throw new StudentPictureException("不匹配");
        }
        String password = ServletRequestUtils.getStringParameter(request.getRequest(), "password");
        String name = ServletRequestUtils.getStringParameter(request.getRequest(), "username");
        redisTemplate.opsForValue().set(name,password,60, TimeUnit.SECONDS);
    }

    public FailureLogin getFailureLogin() {
        return failureLogin;
    }

    public void setFailureLogin(FailureLogin failureLogin) {
        this.failureLogin = failureLogin;
    }

    public StringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

}
