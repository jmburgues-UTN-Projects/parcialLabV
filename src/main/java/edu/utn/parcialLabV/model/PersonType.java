package edu.utn.parcialLabV.model;

public enum PersonType {
    JUGADOR("JUGADOR"),
    REPRESENTANTE("REPRESENTANTE");

    private String description;

    PersonType(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
