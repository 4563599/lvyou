package cn.lyy.service;

import cn.lyy.dao.UserDao;
import cn.lyy.dao.UserDaoImpl;
import cn.lyy.domain.User;

public class UserServiceImpl implements UserService {
    @Override
    public boolean register(User user) {
        UserDao userDao = new UserDaoImpl();
        User u = userDao.findUserByUsername(user.getUsername());
        if (u != null) {
            return false;
        }

        userDao.save(user);
        return true;
    }

    @Override
    public User login(User user) {
        UserDao userDao = new UserDaoImpl();
        return userDao.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
