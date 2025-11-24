package com.digis01.CAlvarezProgramacionNCapasOctubre2025.ML;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Estado {
    
    @JsonProperty("idEstdo")
    private int IdEstado;
    
    @JsonProperty("nombre")
    private String Nombre;
    
    @JsonProperty("paisJPA")
    public Pais Pais;
    
    public Estado(){}
    
    // Getters y Setters
    public int getIdEstado() {
        return IdEstado;
    }
    public void setIdEstado(int IdEstado) {
        this.IdEstado = IdEstado;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Pais getPais() {
        return Pais;
    }

    public void setPais(Pais Pais) {
        this.Pais = Pais;
    }
    
    
}
