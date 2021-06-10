package edu.utn.parcialLabV.controller;

import edu.utn.parcialLabV.api.ApiCallService;
import edu.utn.parcialLabV.api.DolarApiResponse;
import edu.utn.parcialLabV.model.*;
import edu.utn.parcialLabV.model.dto.TicketDTO;
import edu.utn.parcialLabV.service.CumpleanitoService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class CumpleanitoController {

    private ApiCallService apiCallService;
    private CumpleanitoService cumpleanitoService;

    @Autowired
    public CumpleanitoController(ApiCallService apiCallService, CumpleanitoService cumpleanitoService) {
        this.apiCallService = apiCallService;
        this.cumpleanitoService = cumpleanitoService;
    }

    @SneakyThrows
    @GetMapping
    public ResponseEntity<List<TicketDTO>> getTotalTickets(@PathVariable Integer idCumple) {
        List<TicketDTO> totalTickets = cumpleanitoService.getTotalTickets(idCumple);
        HttpStatus httpStatus = totalTickets.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;

        return ResponseEntity.status(httpStatus).body(totalTickets);
    }
}
