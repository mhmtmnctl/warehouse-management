package com.depo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.depo.domain.Role;
import com.depo.enums.RoleType;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByType(RoleType type);
}