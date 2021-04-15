package edu.utn.parcialLabV.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
public class Jugador extends Person{
    private Float peso;
    private Float altura;
    private Integer goles;
    private Integer minutosJugados;
    @OneToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;
    private LocalDateTime fechaNacimiento;

    @Override
    public PersonType personType(){
        return PersonType.JUGADOR;
    }
}
