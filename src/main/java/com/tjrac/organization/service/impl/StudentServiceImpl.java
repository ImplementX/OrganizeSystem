package com.tjrac.organization.service.impl;

import com.tjrac.organization.dao.StudentMapper;
import com.tjrac.organization.pojo.Student;
import com.tjrac.organization.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;



    public Student getStudent(int studentId) {
        return studentMapper.selectByPrimaryKey(studentId);
    }

    public Student getStudentByUserId(int userId) {
        return studentMapper.selectByUserId(userId);
    }

    public boolean saveStudent(Student newStudent) {
        return studentMapper.insert(newStudent)>0?true:false;
    }

    public boolean removeStudent(int studentId) {
        return studentMapper.deleteByPrimaryKey(studentId)>0?true:false;
    }

    public boolean updateStudent(Student thisStudent) {
        return studentMapper.updateByPrimaryKey(thisStudent)>0?true:false;
    }

    public List<Student> listAll() {
        return studentMapper.selectAll();
    }
}
