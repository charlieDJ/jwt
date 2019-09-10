package com.example.boot;

import com.example.boot.sender.CallBackSender;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dengjia
 * @date 2019/9/10 11:49
 */
public class PublisherConfirmTest extends  JwtApplicationTests{

    @Autowired
    private CallBackSender sender;

    @Test
    public void topic() throws Exception {
        sender.send("topic.publisher.testRouting", "测试消息");
    }
}
