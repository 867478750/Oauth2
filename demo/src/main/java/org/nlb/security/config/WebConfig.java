package org.nlb.security.config;

import org.nlb.security.util.MyHander;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private MyHander myHander;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myHander);
    }
}
