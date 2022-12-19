package ru.kharitonov.finalsfproject.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kharitonov.finalsfproject.entities.TransactionEntity;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private Long id;
    private double balance;

    @JsonIgnore
    private Set<TransactionDto> transactionDtoSet = new HashSet<>();

}
