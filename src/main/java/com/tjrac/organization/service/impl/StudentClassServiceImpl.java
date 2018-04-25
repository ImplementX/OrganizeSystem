package com.tjrac.organization.service.impl;

import com.tjrac.organization.dao.StudentClassMapper;
import com.tjrac.organization.pojo.StudentClass;
import com.tjrac.organization.service.StudentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentClassServiceImpl implements StudentClassService {
    @Autowired
    StudentClassMapper studentClassMapper;

    public StudentClass getStudentClass(int studentClassId) {
        return studentClassMapper.selectByPrimaryKey(studentClassId);
    }

    public List<StudentClass> listStudentClassByStudentId(int studentId) {
        return studentClassMapper.selectByStudentId(studentId);
    }

    public List<StudentClass> listStudentClassByClassId(int classId) {
        return studentClassMapper.selectByClassId(classId);
    }

    public boolean saveStudentClass(StudentClass newStudentClass) {
        return studentClassMapper.insert(newStudentClass)>0?true:false;
    }

    public boolean removeStudentClass(int studentClassId) {
        return studentClassMapper.deleteByPrimaryKey(studentClassId)>0?true:false;
    }

    public boolean updateStudentClass(StudentClass thisStudentClass) {
        return studentClassMapper.updateByPrimaryKey(thisStudentClass)>0?true:false;
    }

    public List<StudentClass> listAll() {
        return studentClassMapper.selectAll();
    }
}
