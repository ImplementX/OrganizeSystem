package com.tjrac.organization.service.impl;

import com.tjrac.organization.dao.GradeMapper;
import com.tjrac.organization.pojo.Grade;
import com.tjrac.organization.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    GradeMapper gradeMapper;

    public Grade getGrade(int gradeId) {
        return gradeMapper.selectByPrimaryKey(gradeId);
    }

    @Override
    public Grade getGradeByCourseIdAndStudentId( int courseId, int studentId ) {
        return gradeMapper.selectByCourseIdAndStudentId(  courseId,studentId);
    }

    public List<Grade> listGradeByCourseId( int courseId) {
        return gradeMapper.selectByCourseId(courseId);
    }

    public List<Grade> listGradeByStudentId(int studentId) {
        return gradeMapper.selectByStudentId(studentId);
    }

    public boolean saveGrade(Grade newGrade) {
        return gradeMapper.insert(newGrade)>0?true:false;
    }

    public boolean removeGrade(int gradeId) {
        return gradeMapper.deleteByPrimaryKey(gradeId)>0?true:false;
    }

    public boolean updateGrade(Grade thisGrade) {
        return gradeMapper.updateByPrimaryKey(thisGrade)>0?true:false;
    }

    public List<Grade> listAll() {
        return gradeMapper.selectAll();
    }
}
