package com.service;

import com.mq.MessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@EnableBinding(MessageSource.class)
public class TestServiceImpl {

    @Autowired
    MessageSource messageSource;

    public void test(){
        Map<String,Object> map = new HashMap<>();
        map.put("a","a");
        map.put("b","b");
        String type =  "account";
        Message<Map<String, Object>> build = MessageBuilder.withPayload(map).setHeader("type",type).build();
        messageSource.loginStatusOutPut().send(build);
        System.out.println("时间："+System.currentTimeMillis());
    }
}
