package edu.utn.parcialLabV.service;

import edu.utn.parcialLabV.model.Cumpleanitos;
import edu.utn.parcialLabV.repository.CumpleanitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CumpleanitoService {

    @Autowired
    CumpleanitoRepository cumpleanitoRepository;

    public Cumpleanitos findById(Integer id){
        return this.cumpleanitoRepository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cumpeanito not found"));
    }
}
