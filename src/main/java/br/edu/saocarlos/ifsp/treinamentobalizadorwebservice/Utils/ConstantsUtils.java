package br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.Utils;

public enum ConstantsUtils {

    DATABASE("jdbc:sqlite:/home/ubuntu/treinamento-balizador-webservice/src/main/resources/treinamento_balizador_db");

    private String value;

    ConstantsUtils(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
