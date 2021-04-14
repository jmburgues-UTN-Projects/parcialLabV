package edu.utn.parcialLabV.service;

import edu.utn.parcialLabV.model.Currency;
import edu.utn.parcialLabV.model.Person;
import edu.utn.parcialLabV.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepo;

    public void add(Currency cur){
        Integer id = cur.getId();

        if(id != null && this.currencyRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Currency ID" + id + " already exists.");
        }
        else{
            this.currencyRepo.save(cur);
        }
    }

    public Currency findById(Integer id){
        return this.currencyRepo.findById(id)
                .orElseThrow( () -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public List<Currency> getAll(){
        List<Currency> currencyList = this.currencyRepo.findAll();
        if(!currencyList.isEmpty()){
            return currencyList;
        }
        else{
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
    }

    public void delete(Integer id){
        Currency c = findById(id); // verifica que exista o tira not found
        this.currencyRepo.deleteById(c.getId());
    }


}
