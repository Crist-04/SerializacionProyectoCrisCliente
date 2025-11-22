package com.digis01.CAlvarezProgramacionNCapasOctubre2025.ML;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Rol {
    
    @JsonProperty("idRol")
    private int idRol;
    
    @JsonProperty("nombre")
    private String nombre;
    
    public Rol() {
    }
    
    public Rol(int idRol, String nombre) {
        this.idRol = idRol;
        this.nombre = nombre;
    }
    
    public int getIdRol() {
        return idRol;
    }
    
    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
