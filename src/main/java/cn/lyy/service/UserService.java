package cn.lyy.service;

import cn.lyy.domain.User;

public interface UserService {
    boolean register(User user);

    User login(User user);
}
