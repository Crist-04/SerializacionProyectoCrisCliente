package com.digis01.CAlvarezProgramacionNCapasOctubre2025.ML;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Direccion {
    @JsonProperty("IdDireccion")
    private int idDireccion;

    @JsonProperty("Calle")
    private String calle;

    @JsonProperty("NumeroInterior")
    private String numeroInterior;

    @JsonProperty("NumeroExterior")
    private String numeroExterior;

    @JsonProperty("Colonia")
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