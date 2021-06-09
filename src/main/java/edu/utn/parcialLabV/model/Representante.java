package edu.utn.parcialLabV.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Representante extends Person{
    private Integer pesoDeLaBoveda;
    private Integer montoTotal;
    @OneToMany
    @JoinColumn(name = "jugadores_idK")
    private List<Jugador> jugadores;

    @Override
    public PersonType personType(){
        return PersonType.REPRESENTANTE;
    }

    public Integer getMontoTotal() {
        this.montoTotal = 0;
        for (Jugador j : this.jugadores) {
            this.montoTotal += j.getCurrency().getMonto() * j.getCurrency().getCurrencyType().getToPesosConverter();
        }
        return this.montoTotal;
    }

        /*return this.jugadores.stream()
                .map(Jugador::getCurrency)
                .forEach( (c) -> {
                            c.getCurrencyType().getToPesosConverter();
                            c.getMonto();
                        }).reduce(0,Integer::sum);


                        // multiplicar por (c) -> c.getMonto()
                    // hacer reduce(0,Integer::sum); */

    public Integer getPesoDeLaBoveda() {
        return this.getMontoTotal() / 100;
    }
}
