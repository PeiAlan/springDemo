package com.ellison.springdemo.common.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>压测测试接口</p>
 *正常运行：
 * java -jar springdemo-0.0.1-SNAPSHOT.jar
 *
 *二次运行：
 *      jvm设置：
 *          - 堆得初始大小-Xms1500m
 *          - 内存最大大小-Xmx1500m
 * java -jar -Xms1500m -Xmx1500m springdemo-0.0.1-SNAPSHOT.jar
 *
 *三次运行：
 *      jvm设置：
 *      - 堆得初始大小1500m
 *      - 内存最大大小1500m
 *      - -XX：SurvivorRatio = 8。
 *              设置eden空间大小与幸存者空间大小之间的比率。默认情况下，此选项设置为8。以下示例显示如何将eden / survivor空间比率设置为8
 * java -jar -Xms1500m -Xmx1500m -Xmn1000m -XX:SurvivorRatio=8 springdemo-0.0.1-SNAPSHOT.jar
 *
 * @author Ellison Pei
 * @date 2020/8/14 12:36
 **/
@RestController
@RequestMapping("/jvm")
public class JvmController {

    /**
     * 压测  测试get接口
     * ab -c 10 -n 100000 http://127.0.0.1:8080/jvm/heap
     * ab -c 100 -n 100000 http://127.0.0.1:8080/jvm/heap
     * ab -c 1000 -n 100000 http://127.0.0.1:8080/jvm/heap
     * @return
     */
    @GetMapping("/heap")
    public String heap(){
        List<Byte[]> list = new ArrayList<Byte[]>();
        Byte[] b = new Byte[1024*1024];
        list.add(b);
        return "success";
    }

    /**
     * 压测POST接口测试
     *
     * username=ellison&password=123456 -> post.txt
     * ab -n 100 -c 10 -p 'post.txt' -T 'application/x-www-form-urlencoded' 'http://127.0.0.1:8080/jvm/post'
     *
     *
     * 参数的含义：
     *      -n：总请求次数（最小默认为 1）；
     *      -c：并发次数（最小默认为 1 且不能大于总请求次数，例如：10 个请求，10 个并发，实际就是 1 人请求 1 次）；
     *      -p：post 参数文档路径（-p 和 -T 参数要配合使用）；
     *      -T：header 头内容类型（此处切记是大写英文字母 T）；
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/post")
    public String post(@RequestParam(value = "username", defaultValue = "")String username,
                       @RequestParam(value = "password", defaultValue = "")String password){
        List<Byte[]> list = new ArrayList<Byte[]>();
        Byte[] b = new Byte[1024*1024];
        list.add(b);
        return "success";
    }
}
