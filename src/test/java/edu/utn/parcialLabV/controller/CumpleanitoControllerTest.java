package edu.utn.parcialLabV.controller;

import edu.utn.parcialLabV.AbstractController;
import edu.utn.parcialLabV.api.ApiCallService;
import edu.utn.parcialLabV.model.dto.TicketDTO;
import edu.utn.parcialLabV.service.CumpleanitoService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = CumpleanitoController.class)
public class CumpleanitoControllerTest extends AbstractController {

    @MockBean
    private ApiCallService apiCallService;

    @MockBean
    private CumpleanitoService cumpleanitoService;

    private CumpleanitoController cumpleanitoController;
/*
    @Test
    public void testGetTotalTicketsHappyPath() throws IOException, InterruptedException {
        Integer idCumple = 1;
        List<TicketDTO> ticketList = new ArrayList<>();

        ticketList.add(TicketDTO.builder().nombre("USD").ammount(95.0F).build());
        ticketList.add(TicketDTO.builder().nombre("EUR").ammount(110F).build());

        when(apiCallService.getDolarPrice()[0].getCasa().getCompra()).thenReturn(95.0F);
        when(apiCallService.getEuroPrice().getDolar().getCompra()).thenReturn(110F);

        List<TicketDTO> response = cumpleanitoController.getTotalTickets(1);

        Assert.assertEquals(ticketList.get(0).getAmmount(),response.get(0).getAmmount());
    }*/
}


