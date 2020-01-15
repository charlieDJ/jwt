package com.example.boot.other;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dengjia on 2020/1/10
 */
public class AddRemoveListElement {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");

        for (String s : list) {
            if (s.equals("B")) {
                list.remove(s);
            }
        }
    }
}
