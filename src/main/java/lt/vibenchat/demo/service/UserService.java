package lt.vibenchat.demo.service;

import lt.vibenchat.demo.Mapper;
import lt.vibenchat.demo.dao.UserDao;
import lt.vibenchat.demo.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDao userDao;
    private final Mapper mapper;

    @Autowired
    public UserService(UserDao userDao, Mapper mapper) {
        this.userDao = userDao;
        this.mapper = mapper;
    }

    public void createUser(UserDto userDto) {
        userDao.save(mapper.toUser(userDto));
    }

    public void updateUser(UserDto userDto) {
        userDao.save(mapper.toUser(userDto));
    }

    public UserDto getUserById(Long id) {
        return mapper.toUserDto(userDao.getByID(id));
    }

    public void deleteById(Long id) {
        userDao.deleteByID(id);
    }
}
