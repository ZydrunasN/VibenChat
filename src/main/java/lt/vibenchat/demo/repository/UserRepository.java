package lt.vibenchat.demo.repository;

import lt.vibenchat.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User,Long> {
}
