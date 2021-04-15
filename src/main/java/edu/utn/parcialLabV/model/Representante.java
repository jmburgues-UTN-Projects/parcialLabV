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

    public void calcularMontos(){
        Integer montoTotalPesos = 0;

        for(Jugador j : jugadores){
            CurrencyType currType = j.getCurrency().getCurrencyType();
            if(currType == CurrencyType.DOLARES){
                montoTotalPesos += j.getCurrency().getMonto() * currType.getToPesosConverter(); // magic number..
            }
            else if (currType.equals(CurrencyType.EUROS)){
                montoTotalPesos += j.getCurrency().getMonto() * currType.getToPesosConverter();
            }
            else{
                montoTotalPesos += j.getCurrency().getMonto();
            }
      }
        this.montoTotal = montoTotalPesos;
        this.pesoDeLaBoveda = montoTotalPesos / 100;
    }
}
