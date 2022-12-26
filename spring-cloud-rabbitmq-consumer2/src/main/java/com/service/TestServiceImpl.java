package com.service;

import com.mq.MessageSink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;


@Service
@EnableBinding(MessageSink.class)
public class TestServiceImpl {



    @StreamListener(MessageSink.LOGIN_STATUS_INPUT)
    public void inputData(String json) {
        System.out.println("接受到参数：{}"+ json);
    }
}
