package com.tjrac.organization.service;

import com.tjrac.organization.pojo.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher getTeacher(int teacherId);

    Teacher getTeacherByUserId(int userId);

    boolean saveTeacher(Teacher newTeacher);

    boolean removeTeacher(int teacherId);

    boolean updateTeacher(Teacher thisTeacher);

    List<Teacher> listAll();

}
