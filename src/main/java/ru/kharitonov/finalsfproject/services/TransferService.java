package ru.kharitonov.finalsfproject.services;

import ru.kharitonov.finalsfproject.entities.ClientEntity;
import ru.kharitonov.finalsfproject.entities.TransferEntity;

public interface TransferService {
    TransferEntity transferMoney (ClientEntity outcomeId, ClientEntity incomeId);
}
