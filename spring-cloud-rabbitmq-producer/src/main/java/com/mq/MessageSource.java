package com.mq;


import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MessageSource {

    String LOGIN_STATUS_OUTPUT = "loginStatusOutPut";


    @Output(MessageSource.LOGIN_STATUS_OUTPUT)
    MessageChannel loginStatusOutPut();
}
