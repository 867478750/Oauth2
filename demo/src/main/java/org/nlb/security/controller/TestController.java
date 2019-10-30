package org.nlb.security.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.nlb.security.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.print.Pageable;

@Controller
@RequestMapping("tt")
public class TestController {
    @ResponseBody
    @GetMapping("/test1")
    @JsonView(User.UserSimpleView.class)
    public User tt(){
        User user = new User();
        user.setId(1);
        user.setPassword("23");
        return user;
    }

    @ResponseBody
    @GetMapping("/test2")
    @JsonView(User.UserDetailView.class)
    public User tt2(@Validated  @RequestBody User user, BindingResult results){
        if(results.hasErrors()){
            results.getAllErrors().forEach(result -> System.out.println(result.getDefaultMessage()));
        }
        return user;
    }

}
