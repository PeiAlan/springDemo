package com.ellison.springdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>TODO</p>
 *
 * @author Ellison_Pei
 * @date 2021/9/15 10:47
 * @since 1.0
 **/
@SpringBootTest
public class Java8Test {

    @Test
    public void testMap() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("usePut");
        usePut();
        stopWatch.stop();

        stopWatch.start("useCompute");
        useCompute();
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }

    public void usePut() {
        List<String> animals = Arrays.asList("dog", "cat", "cat", "dog", "fish", "dog");
        Map<String, Integer> map = new HashMap<>();
        for (String animal : animals) {
            Integer count = map.get(animal);
            map.put(animal, count == null ? 1 : ++count);
        }
    }

    public void useCompute() {
        List<String> animals = Arrays.asList("dog", "cat", "cat", "dog", "fish", "dog");
        Map<String, Integer> map = new HashMap<>();
        for (String animal : animals) {
            map.compute(animal, (k, v) -> v == null ? 1 : ++v);
        }
    }
}
