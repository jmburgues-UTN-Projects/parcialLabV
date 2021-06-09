package edu.utn.parcialLabV.api;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EuroApiResponse {
    @SerializedName("dolar")
    private Dolar dolar;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public final static class Dolar {
        @SerializedName("nombre")
        private String nombre;
        @SerializedName("compra")
        private float compra;
    }
}
