package com.example.boot.other;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author dengjia on 2020/3/25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SortedTest implements Comparable<SortedTest> {
    private int age;

    public SortedTest(int age) {
        this.age = age;
    }

    //自定义对象，实现compareTo(T o)方法：
    public int compareTo(SortedTest sortedTest) {
        int num = this.age - sortedTest.getAge();
        //为0时候，两者相同：
        if (num == 0) {
            return 0;
            //大于0时，传入的参数小：
        } else if (num > 0) {
            return 1;
            //小于0时，传入的参数大：
        } else {
            return -1;
        }
    }
}
