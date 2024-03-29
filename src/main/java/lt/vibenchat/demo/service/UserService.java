package lt.vibenchat.demo.service;

import lt.vibenchat.demo.mapper.EntityMapper;
import lt.vibenchat.demo.dao.UserDao;
import lt.vibenchat.demo.dto.entityDto.UserDto;
import lt.vibenchat.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserDao userDao;
    private final EntityMapper mapper;

    @Autowired
    public UserService(UserDao userDao, EntityMapper entityMapper) {
        this.userDao = userDao;
        this.mapper = entityMapper;
    }

    public void updateUser(UserDto userDto) {
        userDao.save(mapper.toUser(userDto));
    }

    public void updateUser(User user) {
        userDao.save(user);
    }

    public UserDto getUserDtoById(Long id) {
        return mapper.toUserDto(userDao.getByID(id));
    }

    public User getUserById(Long id) {
        return userDao.getByID(id);
    }

    public void deleteById(Long id) {
        userDao.deleteByID(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.userByUsernameWithAuthorities(username)
                .orElseThrow(() -> new UsernameNotFoundException("'" + username + "' not found!"));
    }
}
