package com.example.proyectofinal.Model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RecetasRepositorio {

    private final RecetaDAO recetaDAO;
    private final Executor databaseExecutor;

    public RecetasRepositorio(Application aplicacion) {
        RecetasDatabase baseDeDatos = RecetasDatabase.obtenerInstancia(aplicacion);
        recetaDAO = baseDeDatos.recetaDAO();
        databaseExecutor = Executors.newSingleThreadExecutor();
    }

    public void insertar(Receta receta) {
        databaseExecutor.execute(() -> recetaDAO.insertar(receta));
    }

    public void actualizar(Receta receta) {
        databaseExecutor.execute(() -> recetaDAO.actualizar(receta));
    }

    public void eliminar(Receta receta) {
        databaseExecutor.execute(() -> recetaDAO.eliminar(receta));
    }

    public void eliminarTodasLasRecetas() {
        databaseExecutor.execute(recetaDAO::eliminarTodasLasRecetas);
    }

    public LiveData<List<Receta>> obtenerTodasLasRecetas() {
        databaseExecutor.execute(recetaDAO::obtenerTodasLasRecetas);
        return null;
    }
}

