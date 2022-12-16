package ru.kharitonov.finalsfproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kharitonov.finalsfproject.entities.ClientEntity;

public interface ClientRepo extends JpaRepository<ClientEntity, Long> {
}
