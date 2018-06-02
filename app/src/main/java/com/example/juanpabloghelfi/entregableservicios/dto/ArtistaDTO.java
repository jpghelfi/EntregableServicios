package com.example.juanpabloghelfi.entregableservicios.dto;

public class ArtistaDTO {

    private String artistId;
    private String nationality;
    private String name;
    private String Influence_by;


    public ArtistaDTO() {

    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfluence_by() {
        return Influence_by;
    }

    public void setInfluence_by(String influence_by) {
        Influence_by = influence_by;
    }
}
