package com.depo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.depo.domain.User;

@Repository
public interface UserRepository extends JpaRepository<Long, User>{

}
