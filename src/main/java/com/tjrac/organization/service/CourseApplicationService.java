package com.tjrac.organization.service;

import com.tjrac.organization.pojo.CourseApplication;

import java.util.List;

public interface CourseApplicationService {
    boolean removeCourseApplication(Integer courseApplicationId);

    boolean saveCourseApplication(CourseApplication newCourseApplication);

    CourseApplication getCourseApplication(Integer courseApplicationId);

    List<CourseApplication> listAll();

    List<CourseApplication> listByStudentId(Integer studentId);

    List<CourseApplication> listByCourseId(Integer courseId);


    boolean updateCourseApplication(CourseApplication thisCourseApplication);
}
