package com.tjrac.organization.service;

import com.tjrac.organization.pojo.Course;

import java.util.List;

public interface CourseService {
    Course getCourse(int courseId);

    List<Course> listCourseByOrganizationId(int organizationId);

    List<Course> listCourseByTeacherId(int teacherId);

    boolean saveCourse(Course course);

    boolean removeCourse(int courseId);

    boolean updateCourse(Course course);

    List<Course> listAll();

    List<Course> listCourseNotFull();
}
