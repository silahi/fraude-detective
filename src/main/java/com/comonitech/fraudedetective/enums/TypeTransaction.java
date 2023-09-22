package com.comonitech.fraudedetective.enums;

public enum TypeTransaction {
    PAIEMENT_CARTE_CREDIT(
            "Un paiement par carte de crédit est une transaction dans laquelle le client paie avec une carte de crédit."),
    VIREMENT("Un virement est une transaction dans laquelle le client transfère de l'argent d'un compte à un autre."),
    RETRAIT("Un retrait est une transaction dans laquelle le client retire de l'argent d'un compte."),
    TRANSFERT_INTERNE(
            "Un transfert interne est une transaction dans laquelle le client transfère de l'argent d'un compte à un autre compte du même client."),
    AUTRE("Autres types de transactions.");

    private String description;

    TypeTransaction(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
