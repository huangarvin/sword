package com.huangsuip.service.user.impl;


import com.huangsuip.common.po.User;
import com.huangsuip.common.po.UserLogin;
import com.huangsuip.service.mapper.UserMapper;
import com.huangsuip.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * @author HuangSuip
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserDetailServiceImpl userDetailService;
    private boolean b = true;

    @Override
    public UserLogin login(String name, String psw) {
        return userDetailService.loadUserByUsername(name);
    }

    @Override
    public void insertUser(User u) {
        userMapper.insert(u);
    }

    @Override
    public User getUserById(Long id) {
        User user = userMapper.selectById(id);
        if (b){
            b = false;
            throw new SecurityException("UserServiceImpl.getUserById");
        }
        b = true;
        return user;
    }
}
