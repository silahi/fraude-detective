package com.comonitech.fraudedetective;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comonitech.fraudedetective.entities.*;
import com.comonitech.fraudedetective.enums.*;
import com.comonitech.fraudedetective.repositories.*;
import com.github.javafaker.Faker;

@Service
public class FakeDataGenerator {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CompteBancaireRepository compteBancaireRepository;

    @Autowired
    private ModeleDetectionFraudeRepository modeleDetectionFraudeRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    AlerteFraudeRepository alerteFraudeRepository;

    @Autowired
    FakeFraudDetector detecteurFraude;

    private final int NUMBER_OF_CLIENT = 500;
    private final int NUMBER_OF_TRANSACTION = 6_000;

    // De janvier 2020 à la date actuelle Septembre 2023
    LocalDate startDate = LocalDate.of(2020, 1, 1);
    LocalDate endDate = LocalDate.now();

    public void generateData() {
        Faker faker = new Faker();
        List<Client> clients = generateClients(faker);
        List<CompteBancaire> comptesBancaires = generateBankAccounts(faker, clients);
        generateFraudModels();
        generateTransactions(faker, comptesBancaires);
    }

    private List<Client> generateClients(Faker faker) {
        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_CLIENT; i++) {
            Client client = new Client();
            client.setNom(faker.name().fullName());
            client.setAdresse(faker.address().fullAddress());
            client.setNumeroTelephone(faker.phoneNumber().phoneNumber());
            client.setEmail(faker.internet().emailAddress());
            clients.add(client);

            System.out.println(formatColoredText("CLIENT: ", ConsoleColor.YELLOW) + i
                    + formatColoredText("\tNOM: ", ConsoleColor.CYAN) + client.getNom()
                    + formatColoredText("\tTEL: ", ConsoleColor.CYAN) + client.getNumeroTelephone());

        }
        clientRepository.saveAll(clients);
        return clients;
    }

    private List<CompteBancaire> generateBankAccounts(Faker faker, List<Client> clients) {
        List<CompteBancaire> comptesBancaires = new ArrayList<>();
        for (Client client : clients) {
            CompteBancaire compteBancaire = new CompteBancaire();
            compteBancaire.setSolde(faker.number().randomDouble(2, 100, 10000));
            compteBancaire.setNumeroCarteCredit(faker.finance().creditCard());
            compteBancaire.setClient(client);
            compteBancaireRepository.save(compteBancaire);
            comptesBancaires.add(compteBancaire);

            System.out.println(formatColoredText("COMPTE: " + compteBancaire.getNumeroCarteCredit(), ConsoleColor.BLUE)
                    + formatColoredText("\tSOLDE: " + compteBancaire.getSolde(), ConsoleColor.GREEN));
        }
        return comptesBancaires;
    }

    private void generateFraudModels() {
        for (int i = 0; i < TypeModeleDetection.values().length; i++) {
            ModeleDetectionFraude modeleDetectionFraude = new ModeleDetectionFraude();
            TypeModeleDetection model = TypeModeleDetection.values()[i];
            modeleDetectionFraude.setType(model);
            modeleDetectionFraude.setParametres(ParametresModeleDetection.values()[i]);
            modeleDetectionFraudeRepository.save(modeleDetectionFraude);

            System.out.println(formatColoredText("MODEL: ", ConsoleColor.MAGENTA)
                    + formatColoredText("\t: " + modeleDetectionFraude.getType(), ConsoleColor.GREEN));
        }
    }

    private void generateTransactions(Faker faker, List<CompteBancaire> comptesBancaires) {
        for (int i = 0; i < NUMBER_OF_TRANSACTION; i++) {
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
            transaction.setCompteDebit(comptesBancaires.get(faker.number().numberBetween(0, comptesBancaires.size())));
            transaction.setCompteCredit(comptesBancaires.get(faker.number().numberBetween(0, comptesBancaires.size())));
            transaction.setModeleDetection(
                    modeleDetectionFraudeRepository.findById(faker.number().numberBetween(1L, 6L)).orElse(null));
            transactionRepository.save(transaction);

            System.out.println(formatColoredText("TRANSACTION : ", ConsoleColor.YELLOW) + (i + 1) + "  ----- "
                    + formatColoredText("TYPE: ", ConsoleColor.RED) + transaction.getType() + "  ----- "
                    + formatColoredText("MONTANT: ", ConsoleColor.CYAN) + transaction.getMontant());

            AlerteFraude alerte = detecteurFraude.detecterFraude(transaction);

            if (alerte != null)
                alerteFraudeRepository.save(alerte);
        }
    }

    private String formatColoredText(String text, ConsoleColor color) {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_COLOR = "\u001B[" + color.getCode() + "m";
        return ANSI_COLOR + text + ANSI_RESET;
    }

    private enum ConsoleColor {
        RED("31"), GREEN("32"), YELLOW("33"), BLUE("34"), MAGENTA("35"), CYAN("36");

        private final String code;

        ConsoleColor(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
}
