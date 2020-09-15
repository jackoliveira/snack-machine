package com.voll.snackmachine.repositories;

import com.voll.snackmachine.models.SellHistory;
import com.voll.snackmachine.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface SellHistoryRepository extends JpaRepository<SellHistory, String> {
    boolean existsByUserAndDateWhen(User user, LocalDate dateWhen);
}