package com.comonitech.fraudedetective;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.comonitech.fraudedetective.entities.AlerteFraude;
import com.comonitech.fraudedetective.entities.Tranzaction;
import com.comonitech.fraudedetective.enums.EtatAlerteFraude;
import com.github.javafaker.Faker;

@Service
public class FakeFraudDetector {

    public AlerteFraude detecterFraude(Tranzaction transaction) {
        if (montantSuspicion(transaction) || lieuSuspicion(transaction)) {
            return creerAlerte(transaction);
        }
        return null;
    }

    private boolean montantSuspicion(Tranzaction transaction) {
        double seuilMontant = 1000.0;
        return transaction.getMontant() > seuilMontant;
    }

    private boolean lieuSuspicion(Tranzaction transaction) {
        if ((transaction.getLieu().length() == 5) || (transaction.getLieu().length() == 7)) {
            return true;
        }
        return false;
    }

    Faker faker = new Faker();

    private AlerteFraude creerAlerte(Tranzaction transaction) {
        AlerteFraude alerte = new AlerteFraude();
        alerte.setTransaction(transaction);
        alerte.setDateHeureDetection(LocalDateTime.now());
        alerte.setDescription("Alerte : Fraude détectée");
        alerte.setEtat(
                EtatAlerteFraude.values()[faker.number().numberBetween(0, EtatAlerteFraude.values().length)]);
        return alerte;
    }
}
