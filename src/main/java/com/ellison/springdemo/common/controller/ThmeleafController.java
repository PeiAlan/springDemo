package com.ellison.springdemo.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 融合Thmeleaf
 * @author ellisonpei
 */
@Controller
public class ThmeleafController {

    private static final Logger log = LoggerFactory.getLogger(ThmeleafController.class);

    @GetMapping(value = "/hello/{name}")
    public String hello(@PathVariable(value = "name", required = true)String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }
}