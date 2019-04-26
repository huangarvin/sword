package com.huangsuip.test.json;

import java.util.ArrayList;
import java.util.List;
import com.huangsuip.common.po.User;
import com.huangsuip.framework.util.JSONUtils;

/**
 * @author HuangSuip
 */
public class JSONTest {

    public static void main(String[] args) {
        User u = new User();
        u.setUserId(1L);
        u.setName("Huang");
        List<User> list = new ArrayList<>();
        list.add(u);
        String json = JSONUtils.toJSONString(list);
        System.out.println(json);
        List<User> maps = JSONUtils.parseArray(json, User.class);
        System.out.println(maps);
    }
}
