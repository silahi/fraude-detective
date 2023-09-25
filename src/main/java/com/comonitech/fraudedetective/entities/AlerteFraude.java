package com.comonitech.fraudedetective.entities;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.comonitech.fraudedetective.enums.EtatAlerteFraude;

@Data
@Entity
public class AlerteFraude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateHeureDetection;
    private String description;

    @Enumerated(EnumType.STRING)
    private EtatAlerteFraude etat;

    @ManyToOne
    @JoinColumn(name = "transactionId")
    private Tranzaction transaction;
}
