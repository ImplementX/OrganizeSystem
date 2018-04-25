package com.tjrac.organization.service;

import com.tjrac.organization.pojo.Admin;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AdminService {
    Admin getAdmin(int adminId);

    boolean saveAdmin(Admin admin);

    boolean updateAdmin(Admin admin);

    boolean removeAdmin(int adminId);

    Admin getAdminByUserId(int userId);

    List<Admin> listAll();
}
