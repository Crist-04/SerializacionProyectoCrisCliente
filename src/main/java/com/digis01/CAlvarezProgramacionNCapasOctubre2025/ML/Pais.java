package com.digis01.CAlvarezProgramacionNCapasOctubre2025.ML;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pais {
    
    @JsonProperty("idPais")
    private int IdPais;
    
    @JsonProperty("nombre")
    private String Nombre;
    
    public Pais(){}
    
    // Getters y Setters
    public int getIdPais() {
        return IdPais;
    }
    public void setIdPais(int IdPais) {
        this.IdPais = IdPais;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
}
