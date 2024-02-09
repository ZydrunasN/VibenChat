package lt.vibenchat.demo.repository;

import lt.vibenchat.demo.pojo.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {
}
