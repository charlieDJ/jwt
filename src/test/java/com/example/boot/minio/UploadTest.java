package com.example.boot.minio;

import io.minio.MinioClient;
import io.minio.errors.MinioException;
import org.junit.Test;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author dj
 * @date 2021/3/8
 */
public class UploadTest {

    @Test
    public void upload(){
        try {
            // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
            MinioClient minioClient = new MinioClient("http://192.168.132.129:9000", "minioadmin", "minioadmin");
            String bucketName = "glaway";
            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists(bucketName);
            if(isExist) {
                System.out.println("Bucket already exists.");
            } else {
                // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                minioClient.makeBucket(bucketName);
            }
            File file = new File("D:\\temp\\snowdrop-6052942_1920.jpg");
            FileInputStream ins = new FileInputStream(file);
            // 使用putObject上传一个文件到存储桶中。
            minioClient.putObject(bucketName,"snowdrop-6052942_1920.jpg",
                    ins,"image/jpeg");
            System.out.println("upload success");
        } catch(MinioException e) {
            System.out.println("Error occurred: " + e);
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}
