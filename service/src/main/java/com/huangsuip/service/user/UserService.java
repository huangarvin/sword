package com.huangsuip.service.user;

import com.huangsuip.common.po.User;
import com.huangsuip.common.po.UserLogin;

/**
 * @author HuangSuip
 */
public interface UserService {

    UserLogin login(String name, String psw);

    void insertUser(User u);

    User getUserById(Long id);
}
