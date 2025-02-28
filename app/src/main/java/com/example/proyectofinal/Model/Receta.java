package com.example.proyectofinal.Model;

import java.io.Serializable;
import java.util.List;

public class Receta implements Serializable {

    private String id;
    private String nombre;
    private String descripcion;
    private List<String> ingredientes;
    private List<String> pasos;
    private float valoracion;
    private byte[] imagen;
    private List<String> comentarios;
    private String usuarioId;

    public Receta() {
        // Constructor vacío requerido para Firestore
    }

    public Receta(String id, String nombre, String descripcion, List<String> ingredientes, List<String> pasos, float valoracion, byte[] imagen, List<String> comentarios, String usuarioId) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ingredientes = ingredientes;
        this.pasos = pasos;
        this.valoracion = valoracion;
        this.imagen = imagen;
        this.comentarios = comentarios;
        this.usuarioId = usuarioId;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<String> getPasos() {
        return pasos;
    }

    public void setPasos(List<String> pasos) {
        this.pasos = pasos;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public List<String> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<String> comentarios) {
        this.comentarios = comentarios;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }
}