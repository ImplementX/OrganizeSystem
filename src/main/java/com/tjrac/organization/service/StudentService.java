package com.tjrac.organization.service;

import com.tjrac.organization.pojo.Student;

import java.util.List;

public interface StudentService {
    Student getStudent(int studentId);

    Student getStudentByUserId(int userId);

    boolean saveStudent(Student newStudent);

    boolean removeStudent(int studentId);

    boolean updateStudent(Student thisStudent);

    List<Student> listAll();

}
