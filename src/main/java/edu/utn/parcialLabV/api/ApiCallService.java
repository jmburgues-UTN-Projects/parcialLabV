package edu.utn.parcialLabV.api;

import com.google.gson.Gson;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

@Service
public class ApiCallService {

    @CircuitBreaker(name = "dolarApi", fallbackMethod = "fallbackDolar")
    public DolarApiResponse[] getDolarPrice() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.dolarsi.com/api/api.php?type=dolar"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return new Gson().fromJson(response.body(),DolarApiResponse[].class);
    }

    public DolarApiResponse fallbackDolar(Throwable t){
        System.out.println("DolarApi failed: " + t.toString());

        return new DolarApiResponse(new DolarApiResponse.Casa("dolar",95));
    }

    @CircuitBreaker(name = "euroApi", fallbackMethod = "fallbackEuro")
    public EuroApiResponse getEuroPrice() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.dolarsi.com/api/api.php?type=genedolar&opc=ta"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return new Gson().fromJson(response.body(),EuroApiResponse.class);
    }

    public EuroApiResponse fallbackEuro(Throwable t){
        System.out.println("EuroApi failed: " + t.toString());

        return new EuroApiResponse(new EuroApiResponse.Dolar("euro",95));
    }
}