package com.example.juanpabloghelfi.entregableservicios.controller;

import com.example.juanpabloghelfi.entregableservicios.ResultListener;
import com.example.juanpabloghelfi.entregableservicios.dao.ObrasDAO;
import com.example.juanpabloghelfi.entregableservicios.dto.ArtistaDTO;
import com.example.juanpabloghelfi.entregableservicios.dto.ObrasDTO;
import com.example.juanpabloghelfi.entregableservicios.view.fragment.MainFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juanpabloghelfi on 235//18.
 */

public class ObrasController {


    List<ObrasDTO> listObras;
    List<ArtistaDTO> listArtistas;


//    public void getObras(final ResultListener<List<ObrasDTO>> escuchador){
//
//        ObrasDAO obrasDAO = new ObrasDAO();
//        obrasDAO.getObras(new ResultListener<List<ObrasDTO>>() {
//            @Override
//            public void finish(List<ObrasDTO> resultado) {
//                escuchador.finish(resultado);
//            }
//        });
//    }
    public void getObras(final ResultListener<List<ObrasDTO>> escuchador){



        ObrasDAO obrasDAO = new ObrasDAO();
        obrasDAO.getObrasDB(new ResultListener<List<ObrasDTO>>() {
            @Override
            public void finish(List<ObrasDTO> resultado) {

                listObras = resultado;

                getArtistas(new ResultListener<List<ArtistaDTO>>() {
                    @Override
                    public void finish(List<ArtistaDTO> resultado) {
                        listArtistas = resultado;
                        setArtistasInObras(listObras, listArtistas);
                        escuchador.finish(listObras);
                    }
                });

            }
        });
    }

    public void getArtistas(final ResultListener<List<ArtistaDTO>> listener){
        ObrasDAO obrasDAO = new ObrasDAO();
        obrasDAO.getArtistasDB(new ResultListener<List<ArtistaDTO>>() {
            @Override
            public void finish(List<ArtistaDTO> resultado) {
                listener.finish(resultado);
            }
        });
    }

    private void setArtistasInObras(List<ObrasDTO> obrasDTOList, List<ArtistaDTO> artistaDTOList){

        for (ObrasDTO obraDTO: obrasDTOList) {

            for (ArtistaDTO artistaDTO: artistaDTOList) {

                if(artistaDTO.getArtistId().equals(obraDTO.getArtistId())){
                    obraDTO.setArtista(artistaDTO);
                    break;
                }
            }
        }
    }
}





