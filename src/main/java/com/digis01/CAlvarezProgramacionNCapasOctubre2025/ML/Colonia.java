package com.digis01.CAlvarezProgramacionNCapasOctubre2025.ML;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Colonia {
    
    @JsonProperty("idColonia")
    private int idColonia;
    
    @JsonProperty("nombre")
    private String nombre;
    
    @JsonProperty("codigoPostal")
    private String codigoPostal;
    
    @JsonProperty("municipioJPA")
    public Municipio Municipio; 
    

    public Colonia() {
    }
    
    
    public int getIdColonia() {
        return idColonia;
    }
    
    public void setIdColonia(int idColonia) {
        this.idColonia = idColonia;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getCodigoPostal() {
        return codigoPostal;
    }
    
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Municipio getMunicipio() {
        return Municipio;
    }

    public void setMunicipio(Municipio Municipio) {
        this.Municipio = Municipio;
    }
    
    
}