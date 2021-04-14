package edu.utn.parcialLabV.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@NoArgsConstructor
@Data
public class Representante extends Person{
    private List<Jugador> jugadores;
    private Integer pesoDeLaBoveda;
    private Integer montoTotal;

    @Override
    public PersonType personType(){
        return PersonType.REPRESENTANTE;
    }

    public void calcularMontoTotal(){
        Integer montoTotalPesos = 0;

        for(Jugador j : jugadores){
            CurrencyType currType = j.getCurrency().getCurrencyType();
            if(currType.equals(CurrencyType.DOLARES)){
                montoTotalPesos += j.getCurrency().getMonto() * currType.getToPesosConverter(); // magic number..
            }
            else if (currType.equals(CurrencyType.EUROS)){
                montoTotalPesos += j.getCurrency().getMonto() * currType.getToPesosConverter();
            }
            else{
                montoTotalPesos += j.getCurrency().getMonto();
            }
      }
    }
}
