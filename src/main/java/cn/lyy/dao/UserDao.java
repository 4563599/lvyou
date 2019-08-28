package cn.lyy.dao;

import cn.lyy.domain.User;

public interface UserDao {

    User findUserByUsername(String username);

    void save(User user);

    User findUserByUsernameAndPassword(String username, String password);
}
