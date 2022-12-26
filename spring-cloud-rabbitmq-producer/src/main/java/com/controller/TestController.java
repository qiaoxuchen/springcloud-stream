package com.controller;

import com.service.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    TestServiceImpl testService;

    @RequestMapping("/test")
    public void test(){
        testService.test();
    }

}
