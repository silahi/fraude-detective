package com.comonitech.fraudedetective.entities;

import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
public class CompteBancaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double solde;
    private String numeroCarteCredit;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;

    @OneToMany(mappedBy = "compteDebit")
    private List<Tranzaction> transactionsDebit;

    @OneToMany(mappedBy = "compteCredit")
    private List<Tranzaction> transactionsCredit;
}
