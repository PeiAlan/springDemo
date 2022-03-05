package com.ellison.springdemo.common.service.impl;

import com.ellison.springdemo.common.entity.User;
import com.ellison.springdemo.common.mapper.CommonMapper;
import com.ellison.springdemo.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>TODO</p>
 *
 * @author Ellison Pei
 * @date 2020/8/9 21:40
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private CommonMapper commonMapper;

    @Override
    public User addUser(User user) {
        return user;
    }
}
