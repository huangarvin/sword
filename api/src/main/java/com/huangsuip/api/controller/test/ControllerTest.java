package com.huangsuip.api.controller.test;

import com.huangsuip.common.po.User;
import com.huangsuip.common.response.ResponseMessage;
import com.huangsuip.service.user.TestService;
import com.huangsuip.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HuangSuip
 */
@RestController
@RequestMapping("api/test")
public class ControllerTest {

    @Autowired
    private UserService userService;

    @Autowired
    private TestService testService;

    @GetMapping("f1")
    public ResponseMessage f1() {
        testService.all();
        //userService.all();
        return ResponseMessage.ok();
    }

    @GetMapping("f2")
    @Transactional
    public ResponseMessage f2() {
        User u = new User();
        u.setUserId((long) 721);
        u.setName(Math.random() + "");
        userService.updateUser(u);
        return ResponseMessage.ok();
    }
}
