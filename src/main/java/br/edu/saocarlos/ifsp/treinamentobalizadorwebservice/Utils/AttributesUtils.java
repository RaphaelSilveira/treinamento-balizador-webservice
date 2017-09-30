package br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.Utils;

public class AttributesUtils {

    public static String getAttribute(Integer index) {
        String attribute = "";

        switch (index) {
            case 0:
                attribute = "AcionarOuSoltarFreios";
                break;

            case 1:
                attribute = "GirarParaDireita";
                break;

            case 2:
                attribute = "GirarParaEsquerda";
                break;

            case 3:
                attribute = "IdentificacaoRampa";
                break;

            case 4:
                attribute = "ParadaEmergencia";
                break;

            case 5:
                attribute = "ParadaNormal";
                break;

            case 6:
                attribute = "ProsseguirFrente";
                break;

            case 7:
                attribute = "ProsseguirParaProximoSinaleiroComoOrientadoPelaTorreControleDireita";
                break;

            case 8:
                attribute = "ProsseguirParaProximoSinaleiroComoOrientadoPelaTorreControleEsquerda";
                break;

            case 9:
                attribute = "Sinaleiro";
                break;
        }

        return attribute;
    }
}
