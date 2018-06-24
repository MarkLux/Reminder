package com.marklux.reminder.service;

import com.marklux.reminder.common.Encrypt;
import com.marklux.reminder.domain.User;
import com.marklux.reminder.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lumin on 18/6/23.
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public void createUser(User user) throws Exception {
        User oldUser = userMapper.getUserByEmail(user.getEmail());
        if (oldUser != null) {
            throw new Exception("邮箱已被注册");
        }
        user.setPassword(Encrypt.encrypt(user.getPassword()));
        userMapper.createUser(user);
    }

    public User login(User user) throws Exception {
        User storedUser = userMapper.getUserByEmail(user.getEmail());
        if (storedUser == null) {
            throw new Exception("用户不存在");
        }
        if (!Encrypt.check(user.getPassword(), storedUser.getPassword())) {
            throw new Exception("密码错误");
        }else {
            return storedUser;
        }
    }
}
