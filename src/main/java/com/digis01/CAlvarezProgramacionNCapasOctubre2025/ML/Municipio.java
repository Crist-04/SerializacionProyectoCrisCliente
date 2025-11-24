package com.digis01.CAlvarezProgramacionNCapasOctubre2025.ML;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Municipio {
    
    
    @JsonProperty("idMunicipio")
    private int IdMunicipio;
    
    @JsonProperty("nombre")
    private String Nombre;
    
    @JsonProperty("estadoJPA")
    public Estado Estado;
    
    public Municipio(){}
    
    // Getters y Setters
    public int getIdMunicipio() {
        return IdMunicipio;
    }
    public void setIdMunicipio(int IdMunicipio) {
        this.IdMunicipio = IdMunicipio;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Estado getEstado() {
        return Estado;
    }

    public void setEstado(Estado Estado) {
        this.Estado = Estado;
    }
    
    
    
}
