package com.digis01.CAlvarezProgramacionNCapasOctubre2025.ML;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Direccion {
    @JsonProperty("idDireccion")
    private int idDireccion;

    @JsonProperty("calle")
    private String calle;

    @JsonProperty("numeroInterior")
    private String numeroInterior;

    @JsonProperty("numeroExterior")
    private String numeroExterior;

    @JsonProperty("coloniaJPA")
    private Colonia Colonia;
    
    // Constructor vacío
    public Direccion() {
    }
    
    // Getters y Setters
    public int getIdDireccion() {
        return idDireccion;
    }
    
    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }
    
    public String getCalle() {
        return calle;
    }
    
    public void setCalle(String calle) {
        this.calle = calle;
    }
    
    public String getNumeroInterior() {
        return numeroInterior;
    }
    
    public void setNumeroInterior(String numeroInterior) {
        this.numeroInterior = numeroInterior;
    }
    
    public String getNumeroExterior() {
        return numeroExterior;
    }
    
    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public Colonia getColonia() {
        return Colonia;
    }

    public void setColonia(Colonia Colonia) {
        this.Colonia = Colonia;
    }
    
    
}