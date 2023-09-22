package com.comonitech.fraudedetective.enums;

public enum TypeModeleDetection {
    ARBRE_DECISION(
            "Un arbre de décision est un modèle d'apprentissage automatique qui utilise une série de règles pour classer les données."),
    REGRESSION_LOGISTIQUE(
            "Une régression logistique est un modèle d'apprentissage automatique qui prédit la probabilité qu'une observation appartienne à une classe donnée."),
    RESEAU_NEURONES("Un réseau de neurones est un modèle d'apprentissage automatique inspiré du cerveau humain."),
    HYBRIDE("Un modèle hybride combine plusieurs techniques d'apprentissage automatique."),
    AUTRE("Autres modèles de détection de fraude.");

    private String description;

    TypeModeleDetection(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
