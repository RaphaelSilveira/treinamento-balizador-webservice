package br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Signal {

    private String nameFile;
    private String name;
    private String key;
    private Integer insertsInArff;
    private Boolean active;

    @JsonProperty("FileName")
    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("Key")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @JsonProperty("InsertsInArff")
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
