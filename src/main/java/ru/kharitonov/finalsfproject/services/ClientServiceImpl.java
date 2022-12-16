package ru.kharitonov.finalsfproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kharitonov.finalsfproject.entities.ClientEntity;
import ru.kharitonov.finalsfproject.repositories.ClientRepo;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    @Autowired
    private final ClientRepo clientRepo;

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
        if (clientRepo.existsById(id)) {
            ClientEntity clientEntity = clientRepo.findById(id).orElse(new ClientEntity());
            if (clientEntity.getBalance() >= money) {
                clientEntity.setBalance(clientEntity.getBalance() - money);
            } else return "0\nНедостаточно средств";
            clientRepo.save(clientEntity);
            return "1";
        }
        return "Клиент с id: " + id + " не найден";
    }

    @Override
    public String putMoney(Long id, double money) {
        if (clientRepo.existsById(id)) {
            ClientEntity clientEntity = clientRepo.findById(id).orElse(new ClientEntity());
            clientEntity.setBalance(clientEntity.getBalance() + money);
            clientRepo.save(clientEntity);
            return "1";
        }
        return "0\nКлиент с id: " + id + " не найден";
    }

    @Override
    public List<ClientEntity> allClients() {
        return clientRepo.findAll();
    }
}