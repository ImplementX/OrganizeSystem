package com.tjrac.organization.service.impl;

import com.tjrac.organization.dao.UserMapper;
import com.tjrac.organization.pojo.User;
import com.tjrac.organization.service.UserService;
import com.tjrac.organization.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public User getUser(String username){
        return userMapper.selectByUserName(username);
    }

    public boolean saveUser(User newUser){
        return userMapper.insert(newUser)>0?true:false;
    }

    public boolean removeUser(int userId){
        return  userMapper.deleteByPrimaryKey(userId)>0?true:false;

    }


    public boolean updateUser(User thisUser) {
        return userMapper.updateByPrimaryKey(thisUser)>0?true:false;

    }

    public List<User> listAll() {
        return userMapper.selectAll();
    }
}
