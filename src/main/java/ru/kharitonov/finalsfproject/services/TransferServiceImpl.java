package ru.kharitonov.finalsfproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kharitonov.finalsfproject.entities.ClientEntity;
import ru.kharitonov.finalsfproject.entities.TransferEntity;
import ru.kharitonov.finalsfproject.repositories.TransferRepo;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    @Autowired
    private final TransferRepo transferRepo;

    @Override
    public TransferEntity transferMoney(ClientEntity outcomeId, ClientEntity incomeId) {
        TransferEntity transferEntity = TransferEntity.builder()
                .outcomeId(outcomeId)
                .incomeId(incomeId)
                .build();
        TransferEntity savedTransfer = transferRepo.save(transferEntity);
        return savedTransfer;
    }
}