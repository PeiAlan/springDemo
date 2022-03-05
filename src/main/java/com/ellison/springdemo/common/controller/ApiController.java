package com.ellison.springdemo.common.controller;

import com.ellison.springdemo.common.entity.User;
import com.ellison.springdemo.common.entity.dto.UserInputDto;
import com.ellison.springdemo.common.service.UserService;
import com.fasterxml.jackson.databind.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>TODO</p>
 *
 * @author Ellison Pei
 * @date 2020/8/9 21:13
 **/
@RestController
@org.springframework.web.bind.annotation.RequestMapping("api/user/")
public class ApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public User addUser(UserInputDto userInputDto) {
        User user = userInputDto.convertToUser();
        return userService.addUser(user);
    }

    @GetMapping("/get")
    public String get(@RequestParam(value = "id") Integer id) {
        Module module = null;
        return module.getModuleName() + id;
    }

    @PostMapping("/param")
    public User paramTest(@RequestParam(value = "id") Integer id) {
        User user = new User();
        user.setUid(id);
        return userService.addUser(user);
    }

}
