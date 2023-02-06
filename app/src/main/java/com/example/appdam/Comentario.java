package com.example.appdam;

public class Comentario {
    int id;
    String titulo;
    String texto;

    public int getId() {
        return id;
    }

    public Comentario(String titulo, String texto) {
        this.titulo = titulo;
        this.texto = texto;
    }

    public Comentario() {}

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
