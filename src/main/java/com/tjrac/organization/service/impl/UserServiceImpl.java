package com.tjrac.organization.service.impl;

import com.tjrac.organization.dao.UserMapper;
import com.tjrac.organization.pojo.User;
import com.tjrac.organization.service.UserService;
import com.tjrac.organization.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    public User isUser(String username, String password)  {
         User user = userMapper.selectByUserName(username);
        if(EncryptionUtil.EncoderByMd5(password).equals(user.getUserPwd()) ){
            return user;
        }
        return null;
    }



}
