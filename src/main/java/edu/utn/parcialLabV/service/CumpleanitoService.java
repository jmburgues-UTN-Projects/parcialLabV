package edu.utn.parcialLabV.service;

import edu.utn.parcialLabV.api.ApiCallService;
import edu.utn.parcialLabV.model.*;
import edu.utn.parcialLabV.model.dto.TicketDTO;
import edu.utn.parcialLabV.repository.CumpleanitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CumpleanitoService {
    public static final Integer MONTO_POR_PERSONA = 25000;

    private ApiCallService apiCallService;
    private CumpleanitoRepository cumpleanitoRepository;

    @Autowired
    public CumpleanitoService(ApiCallService apiCallService, CumpleanitoRepository cumpleanitoRepository) {
        this.apiCallService = apiCallService;
        this.cumpleanitoRepository = cumpleanitoRepository;
    }

    public Cumpleanitos findById(Integer id){
        return this.cumpleanitoRepository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cumpeanito not found"));
    }

    public List<TicketDTO> getTotalTickets(Integer idCumple) throws IOException, InterruptedException {

        float dolarHoy = apiCallService.getDolarPrice()[0].getCasa().getCompra();
        float euroHoy = apiCallService.getEuroPrice().getDolar().getCompra();

        List<TicketDTO> ticketList = new ArrayList<>();

        Cumpleanitos miCumpleanitos = this.findById(idCumple);

        Set<Person> invitados = miCumpleanitos.getInvitados();

        for(Person invitado : invitados){
            if(invitado instanceof Representante){
                ticketList.add(new TicketDTO(invitado.getName()+" "+invitado.getLastName(),"EUR",MONTO_POR_PERSONA * euroHoy));
            }else{
                Jugador jugador = (Jugador)invitado;
                CurrencyType currency = jugador.getCurrency().getCurrencyType();
                String curr = currency.equals(CurrencyType.EUROS)? "EUR" : "USD";
                float ammount = currency.equals(CurrencyType.EUROS)? MONTO_POR_PERSONA * euroHoy : MONTO_POR_PERSONA * dolarHoy;

                ticketList.add(new TicketDTO(invitado.getName()+" "+invitado.getLastName(),curr,ammount));
            }
        }
        return ticketList;
    }
}
