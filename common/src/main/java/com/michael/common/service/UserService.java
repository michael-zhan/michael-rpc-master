package com.michael.common.service;

import com.michael.common.model.User;

/**
 * @author Michael
 */
public interface UserService {

    /**
     * 返回用户名称
     * @param user
     * @return
     */
    String getName(User user);
}
