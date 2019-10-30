package org.nlb.security.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.nlb.security.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("tt")
@EnableSwagger2
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
            throw new RuntimeException();
        }
        return user;
    }

    @ResponseBody
    @PostMapping("/file")
    public String tt3(MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getName());
        System.out.println(file.getContentType());
        File filename = new File("D:\\a.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        fileOutputStream.write(file.getBytes());
        return "success";
    }

    @ResponseBody
    @GetMapping("/file")
    public String tt4(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition","attachment;filename=data.txt");
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\nlb\\Desktop\\rr.txt"));
            byte[] bytes = fileInputStream.readAllBytes();
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "ss";

    }

}
