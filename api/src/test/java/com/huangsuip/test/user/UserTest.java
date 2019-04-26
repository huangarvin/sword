package com.huangsuip.test.user;

import java.util.ArrayList;
import java.util.List;
import com.huangsuip.api.security.AuthenticationToken;
import com.huangsuip.common.po.UserLogin;

/**
 * @author HuangSuip
 */
public class UserTest {

    public static void main(String[] args) {
        f1();
    }

    private static void f1() {
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        UserLogin userLogin = new UserLogin();
        userLogin.setRoles(roles);
        userLogin.setUsername("admin");
        userLogin.setPassword("123456");
        System.out.println(userLogin.getPassword());
        System.out.println("----------");
        AuthenticationToken token = new AuthenticationToken("admin", "123456");
        System.out.println(token.getCredentials());
    }
}
