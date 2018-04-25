package com.tjrac.organization.service.impl;

import com.tjrac.organization.dao.ClassMapper;
import com.tjrac.organization.pojo.Class;
import com.tjrac.organization.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    ClassMapper classMapper;

    public Class getClass(int classId) {
        return classMapper.selectByPrimaryKey(classId);
    }

    public List<Class> listClassByCourseId(int courseId) {
        return classMapper.selectByCourseId(courseId);
    }

    public boolean saveClass(Class newclass) {
        return classMapper.insert(newclass)>0?true:false;
    }

    public boolean removeClass(int classId) {
        return classMapper.deleteByPrimaryKey(classId)>0?true:false;
    }

    public boolean updateClass(Class thisClass) {
        return classMapper.updateByPrimaryKey(thisClass)>0?true:false;
    }

    public List<Class> listAll() {
        return classMapper.selectAll();
    }
}
