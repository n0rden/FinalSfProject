package ru.kharitonov.finalsfproject.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    @JsonIgnore
    private Long id;
    private Long clientId;
    private int transaction;
    private String date;
    private double transactionAmount;
}
