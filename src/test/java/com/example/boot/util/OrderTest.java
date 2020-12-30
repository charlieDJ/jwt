package com.example.boot.util;

import com.example.boot.common.util.AlphanumComparator;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrderTest {

    @Test
    public void testOrder(){
        List<String> collect = Stream.of("B.2", "A.1", "B.21", "A.11")
                .sorted(Comparator.comparing(Function.identity(), AlphanumComparator.getInstance()).reversed())
                .collect(Collectors.toList());
        for (String s : collect) {
            System.out.println(s);
        }
    }

}
