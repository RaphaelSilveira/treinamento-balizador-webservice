package br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.Utils;

public class AttributesUtils {

    public static String getAttribute(Integer index) {
        String attribute = "";

        switch (index) {
            case 0:
                attribute = "IdentificacaoRampa";
                break;

            case 1:
                attribute = "ProsseguirFrente";
                break;

            case 2:
                attribute = "Sinaleiro";
                break;
        }

        return attribute;
    }
}
