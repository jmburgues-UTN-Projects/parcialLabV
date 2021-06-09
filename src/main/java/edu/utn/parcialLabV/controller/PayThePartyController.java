package edu.utn.parcialLabV.controller;

import edu.utn.parcialLabV.api.ApiCallService;
import edu.utn.parcialLabV.api.DolarApiResponse;
import edu.utn.parcialLabV.model.*;
import edu.utn.parcialLabV.model.dto.TicketDTO;
import edu.utn.parcialLabV.service.CumpleanitoService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
@RequestMapping("/pay")
public class PayThePartyController {

    public static final Integer MONTO_POR_PERSONA = 25000;

    private ApiCallService apiCallService;
    private CumpleanitoService cumpleanitoService;

    @Autowired
    public PayThePartyController(ApiCallService apiCallService, CumpleanitoService cumpleanitoService) {
        this.apiCallService = apiCallService;
        this.cumpleanitoService = cumpleanitoService;
    }

    @SneakyThrows
    @GetMapping
    public List<TicketDTO> getTotalTickets(@PathVariable Integer idCumple){

        float dolarHoy = apiCallService.getDolarPrice().getCasa().getCompra();
        float euroHoy = apiCallService.getEuroPrice().getDolar().getCompra();

        List<TicketDTO> ticketList = new ArrayList<>();

        Cumpleanitos miCumpleanitos = cumpleanitoService.findById(idCumple);
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
