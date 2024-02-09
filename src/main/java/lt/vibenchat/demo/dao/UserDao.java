package lt.vibenchat.demo.dao;

import lt.vibenchat.demo.pojo.Room;
import lt.vibenchat.demo.pojo.User;
import lt.vibenchat.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDao implements CommonDaoActions<User>{
    private final UserRepository repository;

    @Autowired
    public UserDao(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(User user) {
        repository.save(user);
    }

    @Override
    public void update(User user) {
        repository.save(user);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User getByID(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public void deleteByID(Long id) {
        repository.deleteById(id);
    }

    public Optional<User> userByUsernameWithAuthorities(String username) {
        return repository.findUserByUsernameWithAuthorities(username);
    }
}
