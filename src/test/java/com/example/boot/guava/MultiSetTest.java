package com.example.boot.guava;

import com.google.common.collect.HashMultiset;
import org.junit.Test;

/**
 * @author dj
 * @date 2021/2/4
 */
public class MultiSetTest {

    @Test
    public void test(){
        HashMultiset<String> multiset = HashMultiset.create();
        multiset.add("对白");
        multiset.add("螺母");
        multiset.add("对白");
        System.out.println(multiset.count("螺母"));
    }

}
