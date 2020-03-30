package com.example.boot.other;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author dengjia on 2020/3/25
 */
public class MurmurHashTest {

    @Test
    public void generateTest(){
        final HashFunction murmur = Hashing.murmur3_32();
        final HashCode hashCode = murmur.hashString("https://blog.csdn.net/yangguosb/article/details/79516364", StandardCharsets.UTF_8);
        System.out.println("hashCode: " + hashCode);
    }


    @Test
    public void md5Test(){
        final HashFunction hashFunction = Hashing.hmacMd5("11".getBytes());
        final HashCode hashCode = hashFunction.hashString("https://blog.csdn.net/yangguosb/article/details/79516364", StandardCharsets.UTF_8);
        System.out.println("hashCode: " + hashCode);
    }

}
