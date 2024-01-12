package lt.vibenchat.demo.service;

import lt.vibenchat.demo.dao.UserDao;
import lt.vibenchat.demo.pojo.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void createUser(User user) {
        userDao.save(user);
    }

    public void updateUser(User user) {
        userDao.save(user);
    }

    public User getUserById(Long id) {
        return userDao.getByID(id);
    }

    public void deleteById(Long id) {
        userDao.deleteByID(id);
    }
}
