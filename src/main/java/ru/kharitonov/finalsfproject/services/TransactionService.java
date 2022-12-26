package ru.kharitonov.finalsfproject.services;

import ru.kharitonov.finalsfproject.entities.ClientEntity;
import ru.kharitonov.finalsfproject.entities.TransactionEntity;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface TransactionService {
    TransactionEntity addTransaction(ClientEntity clientId, int transactionType, LocalDate transactionDate, double transactionAmount);
}
