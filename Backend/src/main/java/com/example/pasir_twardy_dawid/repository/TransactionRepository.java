package com.example.pasir_twardy_dawid.repository;

import com.example.pasir_twardy_dawid.model.Transaction;
import com.example.pasir_twardy_dawid.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByUser(User user);

    List<Transaction> findAllByUserAndTimestampAfter(User user, LocalDateTime timestamp);
}
