package com.comonitech.fraudedetective; 

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comonitech.fraudedetective.entities.AlerteFraude;
import com.comonitech.fraudedetective.entities.Client;
import com.comonitech.fraudedetective.entities.CompteBancaire;
import com.comonitech.fraudedetective.entities.Tranzaction;
import com.comonitech.fraudedetective.enums.TypeTransaction;
import com.comonitech.fraudedetective.repositories.AlerteFraudeRepository;
import com.comonitech.fraudedetective.repositories.ClientRepository;
import com.comonitech.fraudedetective.repositories.ModeleDetectionFraudeRepository;
import com.comonitech.fraudedetective.repositories.TransactionRepository;
import com.github.javafaker.Faker;

@Service
public class TransactionGenerationService {

    @Autowired
    private ClientRepository clientRepository; 
 

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ModeleDetectionFraudeRepository modeleDetectionFraudeRepository;

    @Autowired
    AlerteFraudeRepository alerteFraudeRepository;

    @Autowired
    FakeFraudDetector detecteurFraude;

    LocalDate startDate = LocalDate.of(2020, 1, 1);
    LocalDate endDate = LocalDate.now();

    public void generateTransactionsAndDetectAlerts(int numberOfTransactions, int numberOfClients) { 
        List<Client> allClients = clientRepository.findAll(); 
        Collections.shuffle(allClients); 
        List<Client> selectedClients = allClients.subList(0, numberOfClients);

        Faker faker = new Faker();

        generateTransactionsForClients(faker, selectedClients, numberOfTransactions);
    }

    private void generateTransactionsForClients(Faker faker, List<Client> clients, int numberOfTransactionsPerClient) {
        for (Client client : clients) {
            for (int i = 0; i < numberOfTransactionsPerClient; i++) {
                Tranzaction transaction = new Tranzaction();
                transaction.setMontant(faker.number().randomDouble(2, 1, 1000));
                java.util.Date date = faker.date().between(Date.valueOf(startDate), Date.valueOf(endDate));
                Instant instant = date.toInstant();
                ZoneId zoneId = ZoneId.systemDefault();
                LocalDateTime dateHeure = instant.atZone(zoneId).toLocalDateTime();
                transaction.setDateHeure(dateHeure);
                transaction.setType(
                        TypeTransaction.values()[faker.number().numberBetween(0, TypeTransaction.values().length)]);
                transaction.setLieu(faker.address().city()); 
                CompteBancaire debitAccount = client.getComptes()
                        .get(faker.number().numberBetween(0, client.getComptes().size()));
                CompteBancaire creditAccount = client.getComptes()
                        .get(faker.number().numberBetween(0, client.getComptes().size()));

                transaction.setCompteDebit(debitAccount);
                transaction.setCompteCredit(creditAccount); 
                transaction.setModeleDetection(
                        modeleDetectionFraudeRepository.findById(faker.number().numberBetween(1L, 6L)).orElse(null)); 
                transactionRepository.save(transaction); 
                AlerteFraude alerte = detecteurFraude.detecterFraude(transaction);

                if (alerte != null)
                    alerteFraudeRepository.save(alerte);
            }
        }
    }

}
