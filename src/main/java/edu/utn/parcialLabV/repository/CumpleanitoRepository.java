package edu.utn.parcialLabV.repository;

import edu.utn.parcialLabV.model.Cumpleanitos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CumpleanitoRepository extends JpaRepository<Cumpleanitos,Integer> {
}
