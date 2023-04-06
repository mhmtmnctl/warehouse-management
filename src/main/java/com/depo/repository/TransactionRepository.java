package com.depo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.depo.domain.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Long, Transaction> {

}
