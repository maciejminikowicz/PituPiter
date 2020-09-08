package pl.mm.pitupiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mm.pitupiter.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    User findByUsername(String username);

    void deleteById(Long id);

    User findByEmail(String email);
}
