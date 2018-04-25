package com.tjrac.organization.service.impl;

import com.tjrac.organization.dao.TeacherMapper;
import com.tjrac.organization.pojo.Teacher;
import com.tjrac.organization.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherMapper teacherMapper;

    public Teacher getTeacher(int teacherId) {
        return teacherMapper.selectByPrimaryKey(teacherId);
    }

    public Teacher getTeacherByUserId(int userId) {
        return teacherMapper.selectByUserId(userId);
    }

    public boolean saveTeacher(Teacher newTeacher) {
        return teacherMapper.insert(newTeacher)>0?true:false;
    }

    public boolean removeTeacher(int teacherId) {
        return teacherMapper.deleteByPrimaryKey(teacherId)>0?true:false;
    }

    public boolean updateTeacher(Teacher thisTeacher) {
        return teacherMapper.updateByPrimaryKey(thisTeacher)>0?true:false;
    }

    public List<Teacher> listAll() {
        return teacherMapper.selectAll();
    }
}
