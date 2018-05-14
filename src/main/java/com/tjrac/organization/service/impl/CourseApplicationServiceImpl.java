package com.tjrac.organization.service.impl;

import com.tjrac.organization.dao.CourseApplicationMapper;
import com.tjrac.organization.pojo.CourseApplication;
import com.tjrac.organization.service.CourseApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseApplicationServiceImpl implements CourseApplicationService {
    @Autowired
    CourseApplicationMapper courseApplicationMapper;


    @Override
    public boolean removeCourseApplication( Integer courseApplicationId ) {
        return courseApplicationMapper.deleteByPrimaryKey( courseApplicationId ) > 0 ? true : false;
    }

    @Override
    public boolean saveCourseApplication( CourseApplication newCourseApplication ) {
        return courseApplicationMapper.insert( newCourseApplication ) > 0 ? true : false;
    }

    @Override
    public CourseApplication getCourseApplication( Integer courseApplicationId ) {
        return courseApplicationMapper.selectByPrimaryKey( courseApplicationId );
    }

    @Override
    public List< CourseApplication > listAll() {
        return courseApplicationMapper.selectAll();
    }

    @Override
    public List< CourseApplication > listByStudentId( Integer studentId ) {
        return courseApplicationMapper.selectByStudentId( studentId );
    }

    @Override
    public List< CourseApplication > listByCourseId( Integer courseId ) {
        return courseApplicationMapper.selectByCourseId( courseId );
    }

    @Override
    public boolean updateCourseApplication( CourseApplication thisCourseApplication ) {
        return courseApplicationMapper.updateByPrimaryKey( thisCourseApplication ) > 0 ? true : false;
    }
}
