package com.example.proyectofinal.UI;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.proyectofinal.Model.Receta;
import com.example.proyectofinal.Model.RecetasRepositorio;

import java.util.Arrays;
import java.util.List;

// Clase que gestiona los datos observables para la vista.
public class RecetasViewModel extends AndroidViewModel {

    private final RecetasRepositorio repositorio; // Referencia al repositorio.
    private final LiveData<List<Receta>> todasLasRecetas; // Lista de películas observables.

    // Constructor del ViewModel.
    public RecetasViewModel(Application aplicacion) {
        super(aplicacion);
        repositorio = new RecetasRepositorio(aplicacion); // Inicializa el repositorio.
        todasLasRecetas = repositorio.obtenerTodasLasRecetas(); // Obtiene todas las películas.

        //Inicializa los datos de muestra al crear el ViewModel.(Ejecutar solo una vez)
        //inicializarPeliculasDeMuestra();
    }

    // Método para insertar películas de ejemplo si la base de datos está vacía.
    private void inicializarRecetasDeMuestra() {
        repositorio.eliminarTodasLasRecetas();
        Receta receta1 = new Receta(1, "Tarta de manzana", "Descripción",Arrays.asList("Manzanas", "Azúcar", "Harina", "Mantequilla"), Arrays.asList("Pelar las manzanas", "Hacer la masa", "Hornear"), 4.5f, null, Arrays.asList("Muy buena", "Me encanta"), "usuario1");
        Receta receta2 = new Receta(2, "Tortilla de patatas","", Arrays.asList("Patatas", "Huevos", "Aceite", "Sal"), Arrays.asList("Pelar las patatas", "Freír las patatas", "Batir los huevos", "Mezclarlo todo", "Hacer la tortilla"), 4.0f, null, Arrays.asList("Muy buena", "Me encanta"), "usuario2");
        Receta receta3 = new Receta(3, "Ensalada César", "",Arrays.asList("Lechuga", "Pollo", "Pan", "Queso", "Salsa César"), Arrays.asList("Lavar la lechuga", "Cortar el pollo", "Tostar el pan", "Mezclarlo todo"), 3.5f, null, Arrays.asList("Muy buena", "Me encanta"), "usuario3");
        Receta receta4 = new Receta(4, "Paella Valenciana","", Arrays.asList("Arroz", "Pollo", "Conejo", "Judías verdes", "Tomate", "Azafrán"), Arrays.asList("Freír la carne", "Añadir el arroz", "Cocer todo junto"), 4.5f, null, Arrays.asList("Muy buena", "Me encanta"), "usuario4");

    }



        // Método para insertar una nueva película.
        public void insertar(Receta receta) {
            repositorio.insertar(receta);
        }

        // Método para actualizar una película existente.
        public void actualizar(Receta receta) {
            repositorio.actualizar(receta);
        }

        // Método para eliminar una película.
        public void eliminar(Receta receta) {
            repositorio.eliminar(receta);
        }

        // Método para eliminar todas las películas.
        public void eliminarTodasLasRecetas() {
            repositorio.eliminarTodasLasRecetas();
        }

        // Método para obtener todas las películas como LiveData.
        public LiveData<List<Receta>> obtenerTodasLasRecetas() {
            return todasLasRecetas;
        }
    }