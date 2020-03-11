package com.example.boot.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;

public class PathTest {
    public static void main(String[] args) {
//        exist();
        createTempFile();
    }

    private static void createTempFile() {
        try {
            final Path tempFile = Files.createTempFile(Paths.get("D:\\"), "我们的歌", ".txt");
            System.out.println("tempFile path: "+ tempFile.toAbsolutePath());
            Thread.sleep(5000);
            Files.deleteIfExists(tempFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void exist() {
        final Path path = Paths.get("D:\\12.xlsx");
        final boolean exists = Files.exists(path, LinkOption.NOFOLLOW_LINKS);
        System.out.println("文件是否存在：" + exists);
        if(!exists){
            FileAttribute<?>[] attrs = {};
            try {
                Files.createFile(path, attrs);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
