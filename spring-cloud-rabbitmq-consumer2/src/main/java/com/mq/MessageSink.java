package com.mq;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MessageSink {

    String LOGIN_STATUS_INPUT = "loginStatusInPut";


    @Input(MessageSink.LOGIN_STATUS_INPUT)
    SubscribableChannel loginStatusInPut();

}
