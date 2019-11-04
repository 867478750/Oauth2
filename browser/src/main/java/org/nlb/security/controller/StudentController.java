package org.nlb.security.controller;

import org.nlb.security.entity.Student;
import org.nlb.security.service.StudentService;
import org.nlb.security.utils.EnconderPassword;
import org.nlb.security.utils.SHA256;
import org.nlb.security.utils.StudentServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentService studentService;
    @ResponseBody
    @GetMapping("/login/{id}")
    public Student login(@PathVariable("id") Integer id){
        Student studentById = studentService.getStudentById(id);
        return studentById;
    }

    @ResponseBody
    @GetMapping("/test")
    public String tt(@RequestParam("name")String name) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return EnconderPassword.EncoderPW(name);
    }

    @ResponseBody
    @GetMapping("/test2")
    public String yy(@RequestParam("name") String name) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return  SHA256.converter(name);
    }

    @PostMapping("new")
    @ResponseBody
    public String createStudent(Student student) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        studentService.insertStudentByStudent(student);
        return "success";
    }

    @RequestMapping("/sessionInvalidate")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String sessionInvalidate(){
        return "session";
    }

}
