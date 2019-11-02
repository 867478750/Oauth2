package org.nlb.security.service.impl;

import org.nlb.security.entity.Student;
import org.nlb.security.mapper.StudenMapper;
import org.nlb.security.service.StudentService;
import org.nlb.security.utils.EnconderPassword;
import org.nlb.security.utils.pwEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudenMapper studenMapper;

    @Override
    public Student getStudentById(Integer id) {
        Student student = studenMapper.getStudentById(id);
        if(student!=null){
        return student;
        }else{
            return null;
        }
    }

    @Override
    public Student getStudentByStudent(Student student) {
        return null;
    }

    @Override
    public Student getStudentByName(String name) {
        Student student = studenMapper.getStudentByName(name);
        if(student!=null){
            return student;
        }else {
            return null;
        }
        }

    @Override
    public void insertStudentByStudent(Student student) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String password = student.getPassword();
        String encoderPW = EnconderPassword.EncoderPW(password);
        student.setPassword(encoderPW);
        studenMapper.insertStudentByStudent(student);
    }
}
