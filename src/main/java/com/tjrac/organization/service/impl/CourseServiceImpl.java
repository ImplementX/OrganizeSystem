package com.tjrac.organization.service.impl;

import com.tjrac.organization.dao.CourseMapper;
import com.tjrac.organization.pojo.Course;
import com.tjrac.organization.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    CourseMapper courseMapper;

    public Course getCourse(int courseId) {
        return courseMapper.selectByPrimaryKey(courseId);
    }

    public List<Course> listCourseByOrganizationId(int organizationId) {
        return courseMapper.selectByOrganizationId(organizationId);
    }

    @Override
    public List< Course > listCourseByTeacherId( int teacherId ) {
        return courseMapper.selectByTeacherId( teacherId );
    }

    public boolean saveCourse( Course course) {
        return courseMapper.insert(course)>0?true:false;
    }

    public boolean removeCourse(int courseId) {
        return courseMapper.deleteByPrimaryKey(courseId)>0?true:false;
    }

    public boolean updateCourse(Course course) {
        return courseMapper.updateByPrimaryKey(course)>0?true:false;
    }

    public List<Course> listAll() {
        return courseMapper.selectAll();
    }

    public List< Course > listCourseNotFull() {
      return  courseMapper.selectIsNotFull();
    }
}
