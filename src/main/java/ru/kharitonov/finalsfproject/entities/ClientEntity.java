package ru.kharitonov.finalsfproject.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "clientsInfo")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double balance;
    @OneToMany(mappedBy = "clientId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TransactionEntity> transactionEntitySet = new HashSet<>();
    @OneToMany(mappedBy = "outcomeId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TransferEntity> transferEntitySet = new HashSet<>();
}
