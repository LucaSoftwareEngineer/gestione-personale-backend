package tool.management.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tool.management.backend.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
