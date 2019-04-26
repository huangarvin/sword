package com.huangsuip.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huangsuip.api.security.AuthenticationToken;
import com.huangsuip.common.po.User;
import com.huangsuip.common.po.UserLogin;
import com.huangsuip.common.response.ResponseMessage;
import com.huangsuip.framework.util.CookieUtils;
import com.huangsuip.framework.util.JwtUtils;
import com.huangsuip.service.generator.IdGenerator;
import com.huangsuip.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

/**
 * @author HuangSuip
 */
@RestController
@RequestMapping
public class LoginController {

    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseMessage<User> login(
            @RequestParam String userName,
            @RequestParam String psw,
            @RequestParam(required = false, defaultValue = "false") Boolean rememberMe,
            HttpServletResponse response
    ) {
        String gensalt = BCrypt.gensalt();
        String hashpw = BCrypt.hashpw(psw, gensalt);
        UserLogin login = userService.login(userName, psw);
        if (login.getUsername() != null) {
            AuthenticationToken token = new AuthenticationToken(userName, hashpw, login.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        User u = new User();
        u.setName(login.getUsername());
        u.setUserId(idGenerator.nextId());
        String jwtToken = JwtUtils.createJwtToken(u, 3000 * 1000);
        CookieUtils.setToken(response, jwtToken);
        return ResponseMessage.ok(u);
    }

    @PostMapping("/logout")
    public ResponseMessage loginOut(HttpServletResponse response) {
        CookieUtils.cleanToken(response);
        SecurityContextHolder.getContext().setAuthentication(null);
        return ResponseMessage.ok();
    }

    @GetMapping("/get/info")
    public ResponseMessage<Object> getJwtInfo(HttpServletRequest request) {
        User user = JwtUtils.parserJwtToken(request);
        return ResponseMessage.ok(user);
    }

    @GetMapping("/get/user/{userId}")
    public ResponseMessage<User> getInfo(@PathVariable("userId") Long userId) {
        User u = new User();
        u.setUserId(idGenerator.nextId());
        u.setName("HuangSuip");
        userService.insertUser(u);
        User user = userService.getUserById(userId);
        return ResponseMessage.ok(user);
    }
}
