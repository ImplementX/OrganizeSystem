package com.tjrac.organization.service;

import com.tjrac.organization.pojo.Class;

import java.util.List;

public interface  ClassService {

    Class getClass(int classId);

    List<Class> listClassByCourseId(int courseId);

    boolean saveClass(Class newclass);

    boolean removeClass(int classId);

    boolean updateClass(Class thisClass);


    List<Class> listAll();
}
