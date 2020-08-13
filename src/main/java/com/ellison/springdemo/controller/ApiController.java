package com.ellison.springdemo.controller;

import com.ellison.springdemo.entity.User;
import com.ellison.springdemo.entity.dto.UserInputDto;
import com.ellison.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>TODO</p>
 *
 * @author Ellison Pei
 * @date 2020/8/9 21:13
 **/
@RestController
@RequestMapping("api/user/")
public class ApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public User addUser(@Validated UserInputDto userInputDto){
        User user = userInputDto.convertToUser();
        return userService.addUser(user);
    }

}
