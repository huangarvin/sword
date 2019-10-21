package com.huangsuip.feng.controller;

import com.huangsuip.feng.service.TestService;
import com.huangsuip.feng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HuangSuip
 */
@RestController
@RequestMapping("api/test")
public class TestController {

    @Autowired
    private UserService userService;
    @Autowired
    private TestService testService;

    @GetMapping
    public Object tran() {
        try {
            //userService.all();
        } catch (Exception e) {}
        try {
            testService.all();
        } catch (Exception e) {}
        return "Test success";
    }
}
