package lt.vibenchat.demo.repository;

import lt.vibenchat.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value =
            "SELECT u FROM user u" +
                    " JOIN FETCH u.authorities" +
                    " WHERE u.username = :username")
    Optional<User> findUserByUsernameWithAuthorities(String username);
}
