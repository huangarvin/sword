package com.huangsuip.feng.service;


import com.huangsuip.feng.po.User;

/**
 * @author HuangSuip
 */
public interface UserService {


    void insertUser(User u);

    User getUserById(Long id);

    void updateUser(User user);

    void all();
}
