package com.example.boot.reflect;

import com.example.boot.model.Response;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ModifierTest {

    @Test
    public void test() throws ClassNotFoundException, NoSuchFieldException {
        Class<?> clazz = Class.forName(Response.class.getName());
        Field ok = clazz.getDeclaredField("OK");
        int okModifiers = ok.getModifiers();
        System.out.println("okModifiers是否是静态变量：" + Modifier.isStatic(okModifiers));

        Field code = clazz.getDeclaredField("code");
        int codeModifiers = code.getModifiers();
        System.out.println("codeModifiers是否是私有变量："+ Modifier.isPrivate(codeModifiers) +
                ", codeModifiers是否是私有变量：" + Modifier.isStatic(codeModifiers));

    }
}
