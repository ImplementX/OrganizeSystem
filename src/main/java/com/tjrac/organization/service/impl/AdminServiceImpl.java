package com.tjrac.organization.service.impl;

import com.tjrac.organization.dao.AdminMapper;
import com.tjrac.organization.pojo.Admin;
import com.tjrac.organization.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    AdminMapper adminMapper;

    public Admin getAdmin(int adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }

    public boolean saveAdmin(Admin admin) {
        return adminMapper.insert(admin)>0?true:false;
    }

    public boolean updateAdmin(Admin admin) {
        return adminMapper.updateByPrimaryKey(admin)>0?true:false;
    }

    public boolean removeAdmin(int adminId) {
        return adminMapper.deleteByPrimaryKey(adminId)>0?true:false;
    }

    public Admin getAdminByUserId(int userId) {
        return adminMapper.selectByUserId(userId);
    }

    public List<Admin> listAll() {
        return adminMapper.selectAll();
    }
}
