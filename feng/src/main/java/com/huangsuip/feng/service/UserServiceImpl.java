package com.huangsuip.feng.service;


import com.alibaba.fastjson.JSON;
import com.huangsuip.feng.dao.UserMapper;
import com.huangsuip.feng.po.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author HuangSuip
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserMapper userMapper;

    private boolean b = true;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertUser(User u) {
        userMapper.insert(u);
    }

    @Override
    public User getUserById(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (b) {
            b = false;
            throw new SecurityException("UserServiceImpl.getUserById");
        }
        b = true;
        return user;
    }

    @Override
    @Transactional
    public void updateUser(final User user) {
        userMapper.updateByPrimaryKey(user);
        if (user.getUserId() > 500) {
            //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException("失败");
        }
    }

    @Override
    public void all() {
        User u = new User();
        try {
            u.setUserId((long) (Math.random() * 1000));
            u.setName("User Inster MinQiong");
            this.insertUser(u);
        } catch (Exception e) {
            logger.info("插入失败: " + JSON.toJSONString(u));
        }
        try {
            u.setName("User Update HuangMinQ");
            this.updateUser(u);
        } catch (Exception e) {
            logger.info("更新失败: " + JSON.toJSONString(u));
            throw e;
        }
    }
}
