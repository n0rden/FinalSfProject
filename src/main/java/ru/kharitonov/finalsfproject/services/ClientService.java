package ru.kharitonov.finalsfproject.services;

import ru.kharitonov.finalsfproject.entities.ClientEntity;

import java.util.List;

public interface ClientService {
    String getMoney(Long id);
    String takeMoney(Long id, double money);
    String putMoney(Long id, double money);
    List<ClientEntity> allClients();
}