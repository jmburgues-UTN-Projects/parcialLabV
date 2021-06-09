package edu.utn.parcialLabV.api;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DolarApiResponse {
    @SerializedName("dolar")
    private Casa casa;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public final static class Casa{
        @SerializedName("nombre")
        private String nombre;
        @SerializedName("compra")
        private float compra;
    }
}


