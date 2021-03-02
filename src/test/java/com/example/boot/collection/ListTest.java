package com.example.boot.collection;

import org.apache.commons.collections4.ListUtils;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author dj
 * @date 2021/3/2
 */
public class ListTest {

    @Test
    public void partition(){
        int targetSize = 100;
        List<Integer> largeList = IntStream.range(1, 9999).boxed().collect(Collectors.toList());
        List<List<Integer>> lists = ListUtils.partition(largeList, targetSize);
        for (List<Integer> list : lists) {
            System.out.println(list.size());
        }
    }

    @Test
    public void partitionByJava8(){
        final AtomicInteger counter = new AtomicInteger(0);
        List<Integer> inputList = IntStream.range(1, 99).boxed().collect(Collectors.toList());
        int size = 10;
        Map<Integer, List<Integer>> map = inputList.stream()
                .peek(e-> System.out.println("counter: " + counter))
                .collect(Collectors.groupingBy(s -> counter.getAndIncrement() / size));
        Collection<List<Integer>> values = map.values();
        for (List<Integer> value : values) {
            System.out.println(value.size());
        }
    }
}
