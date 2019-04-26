package com.huangsuip.service.user.impl;

import java.util.ArrayList;
import java.util.List;
import com.huangsuip.common.po.User;
import com.huangsuip.common.po.UserLogin;
import com.huangsuip.framework.util.JSONUtils;
import com.huangsuip.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author HuangSuip
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserLogin loadUserByUsername(String username) throws UsernameNotFoundException {
        List<String> roles = new ArrayList<>();

        UserLogin userLogin = new UserLogin();
        if ("admin".equalsIgnoreCase(username)) {
            roles.add("ADMIN");
            userLogin.setRoles(roles);
            userLogin.setUsername(username);
            userLogin.setPassword("123456");
            return userLogin;
        }
        if ("anon".equalsIgnoreCase(username)) {
            userLogin.setRoles(roles);
            userLogin.setUsername(username);
            userLogin.setPassword("123456");
            return userLogin;
        }
        throw new UsernameNotFoundException(username);
    }
}
