package ru.kharitonov.finalsfproject.mappers;

import ru.kharitonov.finalsfproject.dtos.ClientDto;
import ru.kharitonov.finalsfproject.entities.ClientEntity;

import java.util.stream.Collectors;

public class ClientMapper {
    public static ClientDto entityToDto(ClientEntity clientEntity) {
        return ClientDto.builder()
                .id(clientEntity.getId())
                .balance(clientEntity.getBalance())
                .transactionDtoSet(clientEntity.getTransactionEntitySet().stream().map(TransactionMapper::entityToDto).collect(Collectors.toSet()))
                .build();
    }
}