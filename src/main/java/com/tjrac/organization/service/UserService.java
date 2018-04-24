package com.tjrac.organization.service;

import com.tjrac.organization.pojo.User;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface UserService{
    User isUser(String username, String password);

}
