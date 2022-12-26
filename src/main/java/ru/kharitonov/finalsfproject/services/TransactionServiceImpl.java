package ru.kharitonov.finalsfproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kharitonov.finalsfproject.entities.ClientEntity;
import ru.kharitonov.finalsfproject.entities.TransactionEntity;
import ru.kharitonov.finalsfproject.repositories.TransactionRepo;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private final TransactionRepo transactionRepo;

    @Override
    public TransactionEntity addTransaction(ClientEntity clientEntity, int transactionType, LocalDate transactionDate, double transactionAmount) {
        TransactionEntity transactionEntity = TransactionEntity.builder()
                .clientId(clientEntity)
                .transaction(transactionType)
                .date(transactionDate)
                .transactionAmount(transactionAmount)
                .build();
        TransactionEntity savedTransaction = transactionRepo.save(transactionEntity);
        return savedTransaction;
    }
}