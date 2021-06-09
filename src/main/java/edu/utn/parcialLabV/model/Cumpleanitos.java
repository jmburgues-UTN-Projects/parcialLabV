package edu.utn.parcialLabV.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
public class Cumpleanitos {
    @Id
    private Integer id;
    private LocalDate fecha;
    @OneToMany
    @JoinColumn(name = "cumpleanito")
    private Set<Person> invitados;
}
