package ru.kharitonov.finalsfproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kharitonov.finalsfproject.entities.TransactionEntity;

public interface TransactionRepo extends JpaRepository<TransactionEntity, Long> {
}
