package com.depo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.depo.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@EntityGraph(attributePaths = "roles")
    Optional<User> findByEmail(String email);
}
