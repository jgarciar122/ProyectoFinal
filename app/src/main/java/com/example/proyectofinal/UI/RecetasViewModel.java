package com.example.proyectofinal.UI;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.proyectofinal.Model.Receta;
import com.example.proyectofinal.repository.RecetasRepositorio;


import java.util.List;

// Clase que gestiona los datos observables para la vista.
public class RecetasViewModel extends AndroidViewModel {

    private final RecetasRepositorio repositorio; // Referencia al repositorio.
    private final MutableLiveData<List<Receta>> todasLasRecetas; // Lista de recetas observables.

    // Constructor del ViewModel.
    public RecetasViewModel(@NonNull Application aplicacion) {
        super(aplicacion);
        repositorio = new RecetasRepositorio(); // Inicializa el repositorio.
        todasLasRecetas = new MutableLiveData<>();
        cargarRecetas();
    }

    // Método para cargar las recetas desde el repositorio.
    private void cargarRecetas() {
        repositorio.leerRecetas(new RecetasRepositorio.RecetasCallback() {
            @Override
            public void onSuccess(List<Receta> lista) {
                todasLasRecetas.setValue(lista);
            }

            @Override
            public void onFailure(String error) {
                // Manejar el error si es necesario
            }
        });
    }

    // Método para insertar una nueva receta.
    public void insertar(Receta receta) {
        repositorio.publicarReceta(receta.getNombre(), receta.getDescripcion(), receta.getIngredientes(), receta.getPasos(), receta.getValoracion(), receta.getImagen(), receta.getComentarios(), new RecetasRepositorio.AuthCallback() {
            @Override
            public void onSuccess() {
                cargarRecetas();
            }

            @Override
            public void onFailure(String error) {
                // Manejar el error si es necesario
            }
        });
    }

    // Método para actualizar una receta existente.
    public void actualizar(Receta receta) {
        // Implementar la lógica de actualización según sea necesario
        // (Firestore no tiene un método específico para actualización, se usa set con merge)
        repositorio.publicarReceta(receta.getNombre(), receta.getDescripcion(), receta.getIngredientes(), receta.getPasos(), receta.getValoracion(), receta.getImagen(), receta.getComentarios(), new RecetasRepositorio.AuthCallback() {
            @Override
            public void onSuccess() {
                cargarRecetas();
            }

            @Override
            public void onFailure(String error) {
                // Manejar el error si es necesario
            }
        });
    }

    // Método para eliminar una receta.
    public void eliminar(Receta receta) {
        repositorio.eliminarReceta(receta.getId(), new RecetasRepositorio.AuthCallback() {
            @Override
            public void onSuccess() {
                cargarRecetas();
            }

            @Override
            public void onFailure(String error) {
                // Manejar el error si es necesario
            }
        });
    }

    // Método para obtener todas las recetas como LiveData.
    public LiveData<List<Receta>> obtenerTodasLasRecetas() {
        return todasLasRecetas;
    }
}