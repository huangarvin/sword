package com.huangsuip.feng.service;


import com.alibaba.fastjson.JSON;
import com.huangsuip.feng.po.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HuangSuip
 */
@Service
public class TestServiceImpl implements TestService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;


    @Override
    public void all() {
        User u = new User();
        try {
            u.setUserId((long) (Math.random() * 1000));
            u.setName("Test Inster MinQiong");
            userService.insertUser(u);
        } catch (Exception e) {
            logger.info("插入失败: " + JSON.toJSONString(u));
        }
        try {
            u.setName("Test Update HuangMinQ");
            userService.updateUser(u);
        } catch (Exception e) {
            logger.info("更新失败: " + JSON.toJSONString(u));
        }
    }
}
