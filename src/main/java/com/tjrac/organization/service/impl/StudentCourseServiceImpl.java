package com.tjrac.organization.service.impl;


import com.tjrac.organization.dao.StudentCourseMapper;
import com.tjrac.organization.pojo.StudentCourse;
import com.tjrac.organization.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {
    @Autowired
    StudentCourseMapper studentCourseMapper;

    public StudentCourse getStudentCourse(int studentCourseId) {
        return studentCourseMapper.selectByPrimaryKey(studentCourseId);
    }

    public List<StudentCourse> listStudentCourseByStudentId(int studentId) {
        return studentCourseMapper.selectByStudentId(studentId);
    }

    public List<StudentCourse> listStudentCourseByCourseId(int courseId) {
        return studentCourseMapper.selectByCourseId(courseId);
    }

    public boolean saveStudentCourse(StudentCourse newStudentCourse) {
        return studentCourseMapper.insert(newStudentCourse)>0?true:false;
    }

    public boolean removeStudentCourse(int studentCourseId) {
        return studentCourseMapper.deleteByPrimaryKey(studentCourseId)>0?true:false;
    }

    public boolean updateStudentCourse(StudentCourse thisStudentCourse) {
        return studentCourseMapper.updateByPrimaryKey(thisStudentCourse)>0?true:false;
    }

    @Override
    public StudentCourse getByStudentIdAndCourseId( int studentId, int courseId ) {
        return studentCourseMapper.selectByStudentIdAndCourseId( studentId, courseId);
    }

    public List<StudentCourse> listAll() {
        return studentCourseMapper.selectAll();
    }
}
