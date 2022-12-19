package ru.kharitonov.finalsfproject.services;

import ru.kharitonov.finalsfproject.dtos.ClientDto;
import ru.kharitonov.finalsfproject.dtos.TransactionDto;
import ru.kharitonov.finalsfproject.entities.TransactionEntity;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ClientService {
    String getMoney(Long id);
    String takeMoney(Long id, double money);
    String putMoney(Long id, double money);
    List<ClientDto> allClients();
    Set<TransactionDto> getOperationList(Long id, Date dateOfStart, Date dateOfEnd);
}