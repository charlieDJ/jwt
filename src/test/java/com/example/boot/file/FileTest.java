package com.example.boot.file;

import com.example.boot.common.util.ThrowingFunction;
import com.example.boot.common.util.ThrowingSupplier;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileTest {

    @Test
    public void getCount() {
        final long count = ThrowingSupplier.wrap(() -> Files.walk(Paths.get("E:\\temp\\jwt")))
                .filter(path -> !Files.isDirectory(path))
                .filter(path -> path.toString().endsWith("log"))
                .flatMap(ThrowingFunction.wrap(Files::lines))
                .filter(line -> !line.trim().isEmpty())
                .count();
        System.out.println("count:" + count);
    }

    @Test
    public void getCountTest() {
        long count = 0;
        try {
            count = Files.walk(Paths.get("E:\\temp\\jwt"))
                    .filter(path -> !Files.isDirectory(path))
                    .filter(path -> path.toString().endsWith("log"))
                    .flatMap(path1 -> {
                        try {
                            return Files.lines(path1);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return Stream.empty();
                        }
                    })
                    .filter(line -> !line.trim().isEmpty())
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("count:" + count);
    }

}
