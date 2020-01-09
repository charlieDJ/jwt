package com.example.boot.common.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;

import java.io.File;

/**
 * @author dengjia on 2019/12/18
 */
public class AliOssClient {
    /** Endpoint以杭州为例，其它Region请按实际情况填写。*/
    private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    /** 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。*/
    private static String accessKeyId = "LTAIeotYEtGZx8oo";
    private static String accessKeySecret = "PIwKagdY9kJEuzNmCUi1iOl3m1xplz";
    private static String bucketName= "mhscbucket";

    public static boolean upload() {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, "111.xls", new File("D:\\temp\\发货单_1.xlsx"));

        // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
         ObjectMetadata metadata = new ObjectMetadata();
//         metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        // metadata.setObjectAcl(CannedAccessControlList.Private);
        metadata.setHeader("Content-Type", "application/vnd.ms-excel");
         putObjectRequest.setMetadata(metadata);

        // 上传文件。
        ossClient.putObject(putObjectRequest);

        // 关闭OSSClient。
        ossClient.shutdown();
        return true;
    }

    public static void main(String[] args) {
        upload();
    }
}
