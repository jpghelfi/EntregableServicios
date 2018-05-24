package com.example.juanpabloghelfi.entregableservicios.dao;

import com.example.juanpabloghelfi.entregableservicios.dto.ContenedorObras;
import com.example.juanpabloghelfi.entregableservicios.ResultListener;
import com.example.juanpabloghelfi.entregableservicios.ServiceObras;
import com.example.juanpabloghelfi.entregableservicios.dto.ObrasDTO;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by juanpabloghelfi on 235//18.
 */

public class ObrasDAO {

    private Retrofit retrofit;

    public ObrasDAO(){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/")
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.client(httpClient.build()).build();
    }

    public void getObras(final ResultListener<List<ObrasDTO>> listenerController) {
        ServiceObras serviceObras = retrofit.create(ServiceObras.class);
        Call<ContenedorObras> call = serviceObras.getProductos();
        call.enqueue(new Callback<ContenedorObras>() {
            @Override
            public void onResponse(Call<ContenedorObras> call, Response<ContenedorObras> response) {
                listenerController.finish(response.body().getResults());
            }

            @Override
            public void onFailure(Call<ContenedorObras> call, Throwable t) {

            }
        });


    }


}
