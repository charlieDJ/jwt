package com.example.boot.util;

import com.example.boot.common.util.RsaUtils;
import org.apache.commons.codec.binary.Base64;

import java.security.KeyPair;

/**
 * @author dengjia on 2020/1/15
 */
public class RsaUtilsTest {
    public static void main(String[] args) {
        try {
            // 生成密钥对
            KeyPair keyPair = RsaUtils.getKeyPair();
            String privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
            System.out.println("私钥:" + privateKey);
            String publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));
            System.out.println("公钥:" + publicKey);
            // RSA加密
            String data = "小名登录密码";
            String encryptData = RsaUtils.encrypt(data, RsaUtils.getPublicKey(publicKey));
            System.out.println("加密后内容:" + encryptData);
            // RSA解密
            String decryptData = RsaUtils.decrypt(encryptData, RsaUtils.getPrivateKey(privateKey));
            System.out.println("解密后内容:" + decryptData);

            // RSA签名
            String sign = RsaUtils.sign(data, RsaUtils.getPrivateKey(privateKey));
            // RSA验签
            boolean result = RsaUtils.verify(data, RsaUtils.getPublicKey(publicKey), sign);
            System.out.print("验签结果:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("加解密异常");
        }
    }
}
