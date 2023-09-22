package com.comonitech.fraudedetective.enums;

public enum ParametresModeleDetection {
    ARBRE_DECISION(
            "Profondeur de l'arbre",
            "Nombre d'itérations",
            "Critère de séparation",
            "Méthode de sélection des variables",
            "Méthode de construction de l'arbre"),
    REGRESSION_LOGISTIQUE(
            "Taux d'apprentissage",
            "Poids initiales",
            "Contrôle de régularisation",
            "Méthode de mise à jour des poids",
            "Critère d'arrêt"),
    RESEAU_NEURONES(
            "Nombre de couches cachées",
            "Nombre de neurones par couche cachée",
            "Fonction d'activation",
            "Méthode d'apprentissage",
            "Critère d'arrêt"),
    HYBRIDE(
            "Modèle 1",
            "Modèle 2",
            "Méthode de combinaison des modèles",
            "Paramètres du modèle 1",
            "Paramètres du modèle 2"),
    AUTRE(
            "Paramètres spécifiques au modèle");

    private String nom;
    private String[] valeurs;

    ParametresModeleDetection(String nom, String... valeurs) {
        this.nom = nom;
        this.valeurs = valeurs;
    }

    public String getNom() {
        return nom;
    }

    public String[] getValeurs() {
        return valeurs;
    }
}
