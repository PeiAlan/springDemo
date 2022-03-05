package com.ellison.springdemo.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ellisonpei
 */
@Controller
@RequestMapping("/fm")
public class FreeMarkerController {

    private static final String  ctx = "freemarker/";
    private static final Logger log = LoggerFactory.getLogger(FreeMarkerController.class);

    @RequestMapping("/index/{name}")
    public String index(@PathVariable(value = "name", required = true)String name, ModelMap model){
        log.info("访问首页，参数为：name="+ name);
        model.put("name",name);
        return ctx + "index";
    }
}
