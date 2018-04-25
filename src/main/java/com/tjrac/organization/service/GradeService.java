package com.tjrac.organization.service;

import com.tjrac.organization.pojo.Grade;

import java.util.List;

public interface GradeService {
    Grade getGrade(int gradeId);

    List<Grade> listGradeByCourseId(int courseId);

    List<Grade> listGradeByStudentId(int studentId);

    boolean saveGrade(Grade newGrade);

    boolean removeGrade(int gradeId);

    boolean updateGrade(Grade thisGrade);

    List<Grade> listAll();
}
