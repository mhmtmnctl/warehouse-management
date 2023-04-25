package com.depo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.depo.domain.Depo;

@Repository
public interface DepoRepository extends JpaRepository<Depo, Long>{

}
