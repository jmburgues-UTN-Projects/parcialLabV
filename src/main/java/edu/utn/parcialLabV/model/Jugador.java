package edu.utn.parcialLabV.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Jugador extends Person{
    private Float peso;
    private Float altura;
    private Integer goles;
    private Integer minutosJugados;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "currency_id")
    private Currency currency;
    private LocalDateTime fechaNacimiento;

    @Override
    public PersonType personType(){
        return PersonType.JUGADOR;
    }
}
