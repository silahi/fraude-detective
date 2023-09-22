package com.comonitech.fraudedetective.enums;

public enum EtatAlerteFraude {
    EN_COURS("L'alerte est en cours d'investigation."),
    RESOLUE("L'alerte a été résolue."),
    IGNOREE("L'alerte a été ignorée."),
    EN_ATTENTE("L'alerte est en attente d'investigation."),
    NON_VALIDEE("L'alerte a été rejetée après enquête."),
    AUTRE("Autres états d'alerte de fraude.");

    private String description;

    EtatAlerteFraude(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
