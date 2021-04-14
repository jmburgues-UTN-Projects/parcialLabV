package edu.utn.parcialLabV.controller;

import edu.utn.parcialLabV.model.Currency;
import edu.utn.parcialLabV.model.Person;
import edu.utn.parcialLabV.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currService;

    @GetMapping
    public List<Currency> getAll(){
        return this.currService.getAll();
    }

    @GetMapping("/{id}")
    public Currency getById(@PathVariable Integer id){
        return this.currService.findById(id);
    }

    @PostMapping
    public void add(@RequestBody Currency p){
        this.currService.add(p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        this.currService.delete(id);
    }
}
