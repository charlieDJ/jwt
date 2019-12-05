package com.example.boot.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.example.boot.JwtApplicationTests;
import com.example.boot.sender.CallBackSender;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @author dengjia
 * @date 2019/9/10 11:49
 */
public class PublisherConfirmTest extends JwtApplicationTests {

    @Autowired
    private CallBackSender sender;

    @Test
    public void topic() throws Exception {
        for (int i = 0; i < 10; i++) {
            JSONObject json = new JSONObject();
            json.put("msg", "测试消息" + i);
            sender.send("topic.publisher.testRouting", json.toJSONString());
        }
        TimeUnit.SECONDS.sleep(2);

    }
}
