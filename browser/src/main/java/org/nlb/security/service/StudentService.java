package org.nlb.security.service;

import org.nlb.security.entity.Student;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface StudentService {
    Student getStudentById(Integer id);
    Student getStudentByStudent(Student student);
    Student getStudentByName(String name);
    void insertStudentByStudent(Student student) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
