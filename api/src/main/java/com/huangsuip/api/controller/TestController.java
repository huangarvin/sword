package com.huangsuip.api.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huangsuip.common.po.User;
import com.huangsuip.common.response.ResponseMessage;
import com.huangsuip.framework.util.JwtUtils;
import com.huangsuip.framework.util.LogUtils;
import com.huangsuip.service.mapper.UserMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HuangSuip
 */
@RestController
@RequestMapping
public class TestController {

    private final UserMapper userMapper;

    public TestController(final UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @GetMapping("test/get/user")
    private Object hello(HttpServletRequest request) {
        LogUtils.info("TestController hello");
        List<Object> sb = new ArrayList<>();
        try {
            User user = JwtUtils.parserJwtToken(request);
            sb.add(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
            sb.add(currentUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseMessage.ok(sb);
    }

    @GetMapping("admin")
    @PreAuthorize("admin")
    private Object admin() {
        LogUtils.info("TestController admin");
        return ResponseMessage.ok("恭喜你 你有admin权限");
    }

    @GetMapping("dba")
    @PreAuthorize("dba")
    //@RolesAllowed("dba")
    private Object dba() {
        LogUtils.info("TestController dba");
        return ResponseMessage.ok("恭喜你 你有dba权限");
    }

    @GetMapping("anonymous")
    private Object anonymous() {
        LogUtils.info("TestController anonymous");
        return ResponseMessage.ok("恭喜你 你有anonymous权限");
    }

    @GetMapping("/error111")
    private Object error(HttpServletResponse response) {
        response.setStatus(200);
        LogUtils.info("TestController error");
        return ResponseMessage.ok("恭喜你 error 了");
    }

    @GetMapping("user-test")
    private Object userTest() {
        userMapper.selectById(1L);
        return ResponseMessage.ok();
    }
}
