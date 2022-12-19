package ru.kharitonov.finalsfproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kharitonov.finalsfproject.dtos.ClientDto;
import ru.kharitonov.finalsfproject.dtos.TransactionDto;
import ru.kharitonov.finalsfproject.entities.ClientEntity;
import ru.kharitonov.finalsfproject.entities.TransactionEntity;
import ru.kharitonov.finalsfproject.mappers.ClientMapper;
import ru.kharitonov.finalsfproject.repositories.ClientRepo;

import java.util.*;


@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    @Autowired
    private final ClientRepo clientRepo;

    @Autowired
    private final TransactionService transactionService;

    @Override
    public String getMoney(Long id) {
        ClientEntity clientEntity;
        String response;
        if (clientRepo.existsById(id)) {
            clientEntity = clientRepo.findById(id).orElse(new ClientEntity());
            response = String.valueOf(clientEntity.getBalance());
        } else response = "-1\nКлиент с id: " + id + " не найден";
        return response;
    }

    @Override
    public String takeMoney(Long id, double money) {
        String response;
        if (clientRepo.existsById(id)) {
            ClientEntity clientEntity = clientRepo.findById(id).orElse(new ClientEntity());
            if (clientEntity.getBalance() >= money) {
                clientEntity.setBalance(clientEntity.getBalance() - money);
                clientRepo.save(clientEntity);
                response = "1";
            } else {
                response = "0\nНедостаточно средств";
            }
            transactionService.addTransaction(clientEntity, 2, new Date(), money);
            return response;
        }
        return "Клиент с id: " + id + " не найден";
    }

    @Override
    public String putMoney(Long id, double money) {
        if (clientRepo.existsById(id)) {
            ClientEntity clientEntity = clientRepo.findById(id).orElse(new ClientEntity());
            clientEntity.setBalance(clientEntity.getBalance() + money);
            clientRepo.save(clientEntity);
            transactionService.addTransaction(clientEntity, 1, new Date(), money);
            return "1";
        }
        return "0\nКлиент с id: " + id + " не найден";
    }

    @Override
    public List<ClientDto> allClients() {
        return clientRepo.findAll().stream()
                .map(ClientMapper::entityToDto)
                .toList();
    }

    @Override
    public Set<TransactionDto> getOperationList(Long id, Date dateOfStart, Date dateOfEnd) {
        Set<TransactionDto> transactionDtos = null;
        if (clientRepo.existsById(id) && dateOfStart == null && dateOfEnd == null) {
            ClientDto clientDto = ClientMapper.entityToDto(clientRepo.findById(id).orElse(new ClientEntity()));
            transactionDtos = clientDto.getTransactionDtoSet();
        }
        return transactionDtos;
    }
}