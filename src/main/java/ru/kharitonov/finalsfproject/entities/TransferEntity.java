package ru.kharitonov.finalsfproject.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "transferInfo")
public class TransferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    ClientEntity outcomeId;
    @ManyToOne(fetch = FetchType.LAZY)
    ClientEntity incomeId;
}
