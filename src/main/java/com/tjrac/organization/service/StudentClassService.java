package com.tjrac.organization.service;

import com.tjrac.organization.pojo.StudentClass;

import java.util.List;

public interface StudentClassService {
    StudentClass getStudentClass(int studentClassId);

    List<StudentClass> listStudentClassByStudentId(int studentId);

    List<StudentClass> listStudentClassByClassId(int classId);

    boolean saveStudentClass(StudentClass newStudentClass);

    boolean removeStudentClass(int studentClassId);

    boolean updateStudentClass(StudentClass thisStudentClass);

    List<StudentClass> listAll();
}
