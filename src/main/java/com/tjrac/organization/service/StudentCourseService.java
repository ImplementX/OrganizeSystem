package com.tjrac.organization.service;

import com.tjrac.organization.pojo.StudentCourse;

import java.util.List;

public interface StudentCourseService {
    StudentCourse getStudentCourse(int studentCourseId);

    List<StudentCourse> listStudentCourseByStudentId(int studentId);

    List<StudentCourse> listStudentCourseByCourseId(int courseId);

    boolean saveStudentCourse(StudentCourse newStudentCourse);

    boolean removeStudentCourse(int studentCourseId);

    boolean updateStudentCourse(StudentCourse thisStudentCourse);

    List<StudentCourse> listAll();
}
