package org.nlb.security.utils;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.util.encoders.HexEncoder;
import org.nlb.security.entity.Student;
import org.nlb.security.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.jar.JarOutputStream;

@Component
public class StudentServiceUtils implements UserDetailsService {
    @Autowired
    StudentService studentService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student studentByName = studentService.getStudentByName(username);
        return new User(username,"123",true,true,true,true,AuthorityUtils.createAuthorityList("root"));
    }
//    private  String password;
//
//    public void setPassword(String s){
//        this.password = s;
//    }
//
//    @Autowired
//    StudentService studentService;
//    @Autowired
//    pwEncoder pwEncoder;
//
//    private Logger logger = LoggerFactory.getLogger(StudentServiceUtils.class);
//
//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        logger.info(username);
//        Student studentByName = studentService.getStudentByName(username);
//        System.out.println(pwEncoder.encode(password));
//        return new User(username,pwEncoder.encode(password),true,true,true,true,AuthorityUtils.createAuthorityList("root"));
//    }

}

