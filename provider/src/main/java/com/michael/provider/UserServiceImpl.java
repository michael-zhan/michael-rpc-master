package com.michael.provider;

import com.michael.common.model.User;
import com.michael.common.service.UserService;

/**
 * @author Michael
 */
public class UserServiceImpl implements UserService {


    @Override
    public String getName(User user) {
        return user.getName();
    }
}
