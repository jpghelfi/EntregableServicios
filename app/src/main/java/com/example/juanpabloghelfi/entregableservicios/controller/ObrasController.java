package com.example.juanpabloghelfi.entregableservicios.controller;

import com.example.juanpabloghelfi.entregableservicios.ResultListener;
import com.example.juanpabloghelfi.entregableservicios.dao.ObrasDAO;
import com.example.juanpabloghelfi.entregableservicios.dto.ObrasDTO;

import java.util.List;

/**
 * Created by juanpabloghelfi on 235//18.
 */

public class ObrasController {


    public void getObras(final ResultListener<List<ObrasDTO>> escuchador){

        ObrasDAO obrasDAO = new ObrasDAO();
        obrasDAO.getObras(new ResultListener<List<ObrasDTO>>() {
            @Override
            public void finish(List<ObrasDTO> resultado) {
                escuchador.finish(resultado);
            }
        });
    }
}
