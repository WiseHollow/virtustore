package net.kwevo.virtustore.security.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoreUserRepository extends JpaRepository<CoreUser, Long> {
    Optional<CoreUser> findByUsername(String username);
    boolean existsByUsername(String username);
}
