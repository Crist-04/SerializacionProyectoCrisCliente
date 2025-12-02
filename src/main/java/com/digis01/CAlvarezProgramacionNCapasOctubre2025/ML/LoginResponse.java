package com.digis01.CAlvarezProgramacionNCapasOctubre2025.ML;

public class LoginResponse {
    
    private boolean correct;
    private String token;
    private String mensaje;
    private int idUsuario;
    private String username;
    private String rol;


    public LoginResponse() {
    }

    public LoginResponse(boolean correct, String token, String mensaje, int idUsuario, String username, String rol) {
        this.correct = correct;
        this.token = token;
        this.mensaje = mensaje;
        this.idUsuario = idUsuario;
        this.username = username;
        this.rol = rol;

    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }


}