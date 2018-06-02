package com.example.juanpabloghelfi.entregableservicios.dto;

import java.io.Serializable;

/**
 * Created by juanpabloghelfi on 235//18.
 */

public class ObrasDTO implements Serializable{

    private String image;
    private String name;
    private String artistId;
    private ArtistaDTO artista;

    public ObrasDTO(){

    }

    public ObrasDTO(String image, String name, String artistId) {
        this.image = image;
        this.name = name;
        this.artistId = artistId;
    }

    public ArtistaDTO getArtista() {
        return artista;
    }

    public void setArtista(ArtistaDTO artista) {
        this.artista = artista;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }
}
