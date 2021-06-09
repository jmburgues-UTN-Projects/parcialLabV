package edu.utn.parcialLabV.controller;

import edu.utn.parcialLabV.model.Person;
import edu.utn.parcialLabV.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;



    @GetMapping
    public List<Person> getAll(){
        return this.personService.getAll();
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable Integer id){
        return this.personService.findById(id);
    }

    @PostMapping
    public void add(@RequestBody Person p){
        this.personService.add(p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        this.personService.delete(id);
    }

    @PutMapping("/{repId}/jugadores/{idJugador}")
    public void addPlayerToRep(@PathVariable Integer repId, @PathVariable Integer idJugador){
        this.personService.addJugadorToRep(repId,idJugador);
    }

}
