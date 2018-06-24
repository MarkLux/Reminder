package com.marklux.reminder.mapper;

import com.marklux.reminder.domain.User;

/**
 * Created by lumin on 18/6/23.
 */
public interface UserMapper {
    void createUser(User user);
    User getUserById(int id);
    User getUserByEmail(String email);
    void updateUser(User user);
}
