package edu.utn.parcialLabV.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Currency {
    @Id
    private Integer id;
    private CurrencyType currencyType;
    private Integer monto;

}
