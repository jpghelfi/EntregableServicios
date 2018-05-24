package com.example.juanpabloghelfi.entregableservicios;

import com.example.juanpabloghelfi.entregableservicios.dto.ContenedorObras;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by juanpabloghelfi on 235//18.
 */

public interface ServiceObras {

    @GET("bins/x858r/")
    Call<ContenedorObras> getProductos();
}
