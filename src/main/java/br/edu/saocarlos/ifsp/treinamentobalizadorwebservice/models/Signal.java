package br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.models;

public class Signal {

    private String nameFile;
    private String name;
    private Integer insertsInArff;
    private Boolean active;

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInsertsInArff() {
        return insertsInArff;
    }

    public void setInsertsInArff(Integer insertsInArff) {
        this.insertsInArff = insertsInArff;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
