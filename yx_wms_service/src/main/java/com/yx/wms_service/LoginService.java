package com.yx.wms_service;

import com.yx.model.User.User;
import com.yx.wms_dao.User.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    @Autowired
    private UserMapper userMapper;

    public User CheckLogin(String phone, String passWord){
        return userMapper.CheckLogin(phone,passWord);
    }
}

