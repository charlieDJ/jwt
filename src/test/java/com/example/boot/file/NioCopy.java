package com.example.boot.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class NioCopy {
    public static void main(String[] args) throws Exception {
        traditionalCopy(new File("D:\\111.zip"), new File("D:\\444.zip"));
//        copyFileByChannel(new File("D:\\111.zip"), new File("D:\\222.zip"));
//        copyByMap(new File("D:\\111.zip"), new File("D:\\333.zip"));
    }

    public static void traditionalCopy(File source, File desc) {
        try (final FileInputStream inputStream = new FileInputStream(source);
             final FileOutputStream outputStream = new FileOutputStream(desc)) {
            byte[] length = new byte[2097152];
            int read;
            final long start = System.currentTimeMillis();
            while ((read = inputStream.read(length)) != -1) {
                outputStream.write(read);
            }
            System.out.println("traditionalCopy耗时：" + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    public static void copyFileByChannel(File source, File dest) {
        try (FileChannel sourceChannel = new FileInputStream(source).getChannel();
             FileChannel targetChannel = new FileOutputStream(dest).getChannel()) {
            int length = 2097152;
            final long start = System.currentTimeMillis();
            while (sourceChannel.position() != sourceChannel.size()) {
                if (sourceChannel.size() - sourceChannel.position() < length) {
                    length = (int) (sourceChannel.size() - sourceChannel.position());
                }
                sourceChannel.transferTo(sourceChannel.position(), length, targetChannel);
                sourceChannel.position(sourceChannel.position() + length);
            }
            System.out.println("copyFileByChannel耗时：" + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static void copyByMap(File source, File dest) {
        try (FileChannel sourceChannel = new FileInputStream(source).getChannel();
             FileChannel targetChannel = new FileOutputStream(dest).getChannel()) {
            int length = 2097152;
            final long start = System.currentTimeMillis();
            while (sourceChannel.position() != sourceChannel.size()) {
                if (sourceChannel.size() - sourceChannel.position() < length) {
                    length = (int) (sourceChannel.size() - sourceChannel.position());
                }
                MappedByteBuffer map = sourceChannel.map(FileChannel.MapMode.READ_ONLY, sourceChannel.position(), length);
                targetChannel.write(map);
                sourceChannel.position(sourceChannel.position() + length);
            }
            System.out.println("copyByMap耗时：" + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
