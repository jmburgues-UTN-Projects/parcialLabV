package edu.utn.parcialLabV.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.data.annotation.AccessType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "personType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Jugador.class, name = "JUGADOR"),
        @JsonSubTypes.Type(value = Representante.class, name = "REPRESENTANTE")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Person {
    @javax.persistence.Id
    private Integer Id;
    @NotNull(message = "Person name is required.")
    private String name;
    @NotNull(message = "Person lastName is required.")
    private String lastName;
    @AccessType(AccessType.Type.PROPERTY)
    public abstract PersonType personType();
}
