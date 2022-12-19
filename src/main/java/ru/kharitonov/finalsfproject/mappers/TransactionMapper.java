package ru.kharitonov.finalsfproject.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kharitonov.finalsfproject.dtos.TransactionDto;
import ru.kharitonov.finalsfproject.entities.TransactionEntity;
import ru.kharitonov.finalsfproject.repositories.ClientRepo;

public class TransactionMapper {

    public static TransactionDto entityToDto(TransactionEntity transactionEntity) {
        return TransactionDto.builder()
                .id(transactionEntity.getId())
                .clientId(transactionEntity.getClientId().getId())
                .transaction(transactionEntity.getTransaction())
                .date(transactionEntity.getDate().toString())
                .transactionAmount(transactionEntity.getTransactionAmount())
                .build();
    }
}