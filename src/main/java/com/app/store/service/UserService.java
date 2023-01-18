package com.app.store.service;

import com.app.store.entity.User;
import com.app.store.entity.UserRole;

import java.util.Set;

public interface UserService {

    public User saveUser(User user, Set<UserRole> userRoles) throws Exception;

    public User getUser(String username);

    public void deleteUser(Long userId);
}
