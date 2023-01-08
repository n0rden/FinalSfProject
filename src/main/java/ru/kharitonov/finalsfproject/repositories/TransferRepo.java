package ru.kharitonov.finalsfproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kharitonov.finalsfproject.entities.TransferEntity;

public interface TransferRepo extends JpaRepository<TransferEntity, Long> {
}
