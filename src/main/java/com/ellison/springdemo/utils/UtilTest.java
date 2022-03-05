package com.ellison.springdemo.utils;

import com.ellison.tool.EllisonStringUtil;
import org.junit.Test;

import java.util.List;

/**
 * <p>TODO</p>
 *
 * @Author Ellison Pei
 * @Date 2021/8/26 10:52
 **/
public class UtilTest {

    @Test
    public void testString(){
        String nvl = EllisonStringUtil.nvl(null, "1111");
        String str = "1900-01-01 2007/08/13 1900.01.01 ,1900 01 01 1900-01.01 ,1900 13 01 1900 02 31";
        List<String> strings = EllisonStringUtil.splitConvertToList(str, ",");
        System.out.println("nvl: " + nvl);
        System.out.println("splitConvertToList: " + strings.toString());
    }


    /**
     * BLOG URL : https://www.jianshu.com/p/461429a5edc9
     *
     * IntStream.generate() 产生一个无限流，这里需要传入一个IntSupplier函数式接口实例 。
     * IntStream.range() 产生指定区间的有序IntStream，这里需要传入一个区间（左闭右开），产生的元素不包含最后一个。
     * IntStream.rangeClosed() 产生指定区间的有序IntStream，与IntStream.range()不同的是，产生的元素包含最后一个，即左闭右闭。
     * IntStream.of() 填充一个或多个int元素构造流。
     * IntStream.empty() 产生一个空元素的流。
     * IntStream.builder() 会产生一个builder用于构建stream，通过builder的add方法添加元素，build方法构造流。
     * IntStream.iterate() 产生一个有序的无限流，需要传入初始值，对元素操作的函数。
     * IntStream.concat() 将两个流合并成一个流。
     *
     * map() 产生的仍然是IntStream，可以对元素进行数学上的操作，加减乘除等等。
     * mapToObj() 转成对象流，例如String等。
     * mapToLong() 转成long类型流。
     * mapToDouble() 转成double类型流。
     * asLongStream() 快速转成Long类型的Stream。
     * asDoubleStream() 快速转成Double类型的Stream。
     *
     */
    @Test
    public void streamTest(){
    }

}
