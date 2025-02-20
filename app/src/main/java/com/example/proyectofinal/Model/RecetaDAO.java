package com.example.proyectofinal.Model;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface RecetaDAO {

    @Insert
    void insertar(Receta receta);

    @Update
    void actualizar(Receta receta);

    @Delete
    void eliminar(Receta receta);

    @Query("SELECT * FROM tabla_recetas ORDER BY nombre ASC")
    LiveData<List<Receta>> obtenerTodasLasRecetas();

    @Query("SELECT * FROM tabla_recetas WHERE nombre = :nombre")
    Receta obtenerRecetaPorNombre(String nombre);

    @Query("DELETE FROM tabla_recetas")
    void eliminarTodasLasRecetas();



}
