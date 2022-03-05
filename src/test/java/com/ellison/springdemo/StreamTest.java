package com.ellison.springdemo;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>TODO</p>
 *
 * @author Ellison Pei
 * @date 2020/8/9 14:53
 **/
@SpringBootTest
public class StreamTest {

    @Test
    public void test1(){
        List<String> names = Arrays.asList("张三","李四","王五","赵柳","张五六七","王少","赵四","张仁","李星");
        List<String> list1 = names.stream().filter(s -> s.equals("王少")).collect(Collectors.toList());
        System.out.println(list1);
        //需求：找出 姓张中名字最长的
        int maxLengthStartWithZ = names.stream()
                .filter(name -> name.startsWith("张"))
                .mapToInt(String::length)
                .max()
                .getAsInt();
        System.out.println(names.get(maxLengthStartWithZ));
    }
}
