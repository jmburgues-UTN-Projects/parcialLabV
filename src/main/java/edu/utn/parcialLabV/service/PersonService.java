package edu.utn.parcialLabV.service;

import edu.utn.parcialLabV.model.Currency;
import edu.utn.parcialLabV.model.Jugador;
import edu.utn.parcialLabV.model.Person;
import edu.utn.parcialLabV.model.Representante;
import edu.utn.parcialLabV.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepo;

    public void add(Person p){
        Integer id = p.getId();

        if(id != null && this.personRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Person ID " + id + " already exists.");
        }
        else{
            this.personRepo.save(p);
        }
    }

    public List<Person> getAll(){
        List<Person> personList = this.personRepo.findAll();
        if(!personList.isEmpty()){
            return personList;
        }
        else{
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
    }

    public Person findById(Integer id){
        Person p = this.personRepo.findById(id)
                    .orElseThrow( () -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        if(p instanceof Representante){
            ((Representante) p).calcularMontoTotal();
        }

        return p;
    }

    public void delete(Integer id){
        Person p = findById(id); // verifica que exista o tira not found
        this.personRepo.deleteById(p.getId());
    }

    public void addJugadorToRep(Integer repId,Integer jugadorId){
        Person jugador = findById(jugadorId);
        Person representante = findById(repId);
        List<Jugador> jugadoresList;

        // corroboro que los IDs sean correctos
        if(jugador instanceof Jugador){
            if(representante instanceof Representante){

                // corroboro si la relacion existe previamente
                jugadoresList = ((Representante) representante).getJugadores();
                if(jugadoresList.contains( ((Jugador)jugador)) ) {
                    throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,"The player " + jugadorId + " is already managed by Mananger "+ repId);
                }
                else{
                    jugadoresList.add((Jugador) jugador);
                }
            }
            else{
                throw new ResponseStatusException(HttpStatus.CONFLICT,"ID " + repId + " is not an existent Manager");
            }
        }
        else{
            throw new ResponseStatusException(HttpStatus.CONFLICT,"ID " + jugadorId + " is not an existent Player");
        }
    }

}
