package br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.models;

import java.util.List;

public class Moviment {

    private List<String> coordinates;
    private String moviment;

    public List<String> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<String> coordinates) {
        this.coordinates = coordinates;
    }

    public String getMoviment() {
        return moviment;
    }

    public void setMoviment(String moviment) {
        this.moviment = moviment;
    }
}
