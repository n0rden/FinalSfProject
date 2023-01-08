package ru.kharitonov.finalsfproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.kharitonov.finalsfproject.dtos.ClientDto;
import ru.kharitonov.finalsfproject.dtos.TransactionDto;
import ru.kharitonov.finalsfproject.entities.ClientEntity;
import ru.kharitonov.finalsfproject.mappers.ClientMapper;
import ru.kharitonov.finalsfproject.repositories.ClientRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    @Autowired
    private final ClientRepo clientRepo;

    @Autowired
    private final TransactionService transactionService;

    @Autowired
    private final TransferService transferService;

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
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
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
            transactionService.addTransaction(clientEntity, 2, LocalDate.now(), money);
            return response;
        }
        return "Клиент с id: " + id + " не найден";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public String putMoney(Long id, double money) {
        if (clientRepo.existsById(id)) {
            ClientEntity clientEntity = clientRepo.findById(id).orElse(new ClientEntity());
            clientEntity.setBalance(clientEntity.getBalance() + money);
            clientRepo.save(clientEntity);
            transactionService.addTransaction(clientEntity, 1, LocalDate.now(), money);
            return "1";
        }
        return "0\nКлиент с id: " + id + " не найден";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public String transferMoney(Long idOutcome, Long idIncome, double amount) {
        if (takeMoney(idOutcome, amount).equals("1")) {
            if (putMoney(idIncome, amount).equals("1")) {
                ClientEntity outcomeId = clientRepo.findById(idOutcome).orElse(new ClientEntity());
                ClientEntity incomeId = clientRepo.findById(idIncome).orElse(new ClientEntity());
                transferService.transferMoney(outcomeId, incomeId);
                return "1";
            }
        }
        return "0";
    }

    @Override
    public List<ClientDto> allClients() {
        return clientRepo.findAll().stream().map(ClientMapper::entityToDto).toList();
    }

    @Override
    public Set<TransactionDto> getOperationList(Long id, String dateOfStart, String dateOfEnd) {
        Set<TransactionDto> transactionDtos = null;
        if (clientRepo.existsById(id)) {
            ClientDto clientDto = ClientMapper.entityToDto(clientRepo.findById(id).orElse(new ClientEntity()));
            transactionDtos = clientDto.getTransactionDtoSet();
            if (dateOfStart != null && dateOfEnd != null) {
                LocalDate minDate = LocalDate.parse(dateOfStart);
                LocalDate maxDate = LocalDate.parse(dateOfEnd);
                if (minDate.isBefore(maxDate) || minDate.isEqual(maxDate)) {
                    transactionDtos = transactionDtos.stream()
                            .filter(t ->
                                    (minDate.isBefore(LocalDate.parse(t.getDate())) || minDate.isEqual(LocalDate.parse(t.getDate())))
                                            && (maxDate.isAfter(LocalDate.parse(t.getDate())) || maxDate.isEqual(LocalDate.parse(t.getDate()))))
                            .collect(Collectors.toSet());
                }
            }
            return transactionDtos;
        }
        return transactionDtos;
    }
}