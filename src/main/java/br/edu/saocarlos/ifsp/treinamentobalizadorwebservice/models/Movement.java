package br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.models;

import java.util.List;

public class Movement {

    private List<String> coordinates;
    private String movement;

    public List<String> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<String> coordinates) {
        this.coordinates = coordinates;
    }

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }
}
