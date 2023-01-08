package ru.kharitonov.finalsfproject.services;

import ru.kharitonov.finalsfproject.dtos.ClientDto;
import ru.kharitonov.finalsfproject.dtos.TransactionDto;

import java.util.List;
import java.util.Set;

public interface ClientService {
    String getMoney(Long id);
    String takeMoney(Long id, double money);
    String putMoney(Long id, double money);
    String transferMoney(Long idOutcome, Long idIncome, double amount);
    List<ClientDto> allClients();
    Set<TransactionDto> getOperationList(Long id, String dateOfStart, String dateOfEnd);
}