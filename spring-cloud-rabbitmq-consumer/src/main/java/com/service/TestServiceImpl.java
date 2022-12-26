package com.service;

import com.mq.MessageSink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Service;


@Service
@EnableBinding(MessageSink.class)
public class TestServiceImpl {

    @StreamListener(MessageSink.LOGIN_STATUS_INPUT)
    public void inputData(String json) {


        System.out.println("inputData 接受到参数：{}"+ json);

    }

//    @StreamListener(MessageSink.LOGIN_STATUS_INPUT)
//    @SendTo("responseMessage")
//    public String inputData(String json) {
//
//
//        System.out.println("inputData 接受到参数：{}"+ json);
//        return "success";
//    }

    @StreamListener(MessageSink.RESPONSE_MESSAGE)
    public void responseMessage(String json) {


        System.out.println("responseMessage 接受到参数：{}"+ json);

    }
}
