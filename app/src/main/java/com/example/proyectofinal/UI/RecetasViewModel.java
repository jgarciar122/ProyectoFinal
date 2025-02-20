package com.example.proyectofinal.UI;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.proyectofinal.Model.Receta;
import com.example.proyectofinal.Model.RecetasRepositorio;

import java.util.Arrays;
import java.util.List;

public class RecetasViewModel extends AndroidViewModel {

    private final RecetasRepositorio repositorio;
    private final LiveData<List<Receta>> todasLasRecetas;

    public RecetasViewModel(Application aplicacion) {
        super();
        repositorio = new RecetasRepositorio(aplicacion);
        todasLasRecetas = repositorio.obtenerTodasLasRecetas();
        //Solo inicializar una vez
        //inicializarRecetasDeMuestra();


    }

    private void inicializarRecetasDeMuestra() {
        Receta receta1 = new Receta(1, "Tarta de manzana", Arrays.asList("Manzanas", "Azúcar", "Harina", "Mantequilla"), Arrays.asList("Pelar las manzanas", "Hacer la masa", "Hornear"), 4.5f, null, Arrays.asList("Muy buena", "Me encanta"), "usuario1");
        Receta receta2 = new Receta(2, "Tortilla de patatas", Arrays.asList("Patatas", "Huevos", "Aceite", "Sal"), Arrays.asList("Pelar las patatas", "Freír las patatas", "Batir los huevos", "Mezclarlo todo", "Hacer la tortilla"), 4.0f, null, Arrays.asList("Muy buena", "Me encanta"), "usuario2");
        Receta receta3 = new Receta(3, "Ensalada César", Arrays.asList("Lechuga", "Pollo", "Pan", "Queso", "Salsa César"), Arrays.asList("Lavar la lechuga", "Cortar el pollo", "Tostar el pan", "Mezclarlo todo"), 3.5f, null, Arrays.asList("Muy buena", "Me encanta"), "usuario3");
        Receta receta4 = new Receta(4, "Paella Valenciana", Arrays.asList("Arroz", "Pollo", "Conejo", "Judías verdes", "Tomate", "Azafrán"), Arrays.asList("Freír la carne", "Añadir el arroz", "Cocer todo junto"), 4.5f, null, Arrays.asList("Muy buena", "Me encanta"), "usuario4");


    }
}
