package com.marklux.reminder.test.mapper;

import com.marklux.reminder.domain.User;
import com.marklux.reminder.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by lumin on 18/6/23.
 */
@SpringBootTest
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void createUserTest() {
        User user = new User();
        user.setUserName("mark");
        user.setEmail("marlx6590@163.com");
        user.setPassword("123456");
        userMapper.createUser(user);
    }
}
