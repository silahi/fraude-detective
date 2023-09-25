package com.comonitech.fraudedetective.entities;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import com.comonitech.fraudedetective.enums.TypeTransaction;

@Data
@Entity
public class Tranzaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double montant;
    private LocalDateTime dateHeure;

    @Enumerated(EnumType.STRING)
    private TypeTransaction type;

    private String lieu;

    @ManyToOne
    @JoinColumn(name = "compteDebitId")
    private CompteBancaire compteDebit;

    @ManyToOne
    @JoinColumn(name = "compteCreditId")
    private CompteBancaire compteCredit;

    @OneToMany(mappedBy = "transaction")
    private List<AlerteFraude> alertes;

    @ManyToOne
    @JoinColumn(name = "modeleDetectionId")
    private ModeleDetectionFraude modeleDetection;
}
