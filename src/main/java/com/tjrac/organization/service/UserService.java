package com.tjrac.organization.service;

import com.tjrac.organization.pojo.User;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {
    User isUser(String username, String password);

    User getUser(String username);

    boolean saveUser(User newUser);

    boolean removeUser(int userId);

    boolean updateUser(User thisUser);

    List<User> listAll();
}
