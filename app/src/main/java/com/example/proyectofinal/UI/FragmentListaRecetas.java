package com.example.proyectofinal.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.R;
import com.example.proyectofinal.databinding.FragmentListaRecetasBinding;


public class FragmentListaRecetas extends Fragment {

    private RecetasViewModel recetasViewModel; // Referencia al ViewModel.
    private RecetasAdapter adaptador; // Adaptador para el RecyclerView.
    private FragmentListaRecetasBinding binding; // Objeto para ViewBinding

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflamos el diseño del fragmento.
        binding = FragmentListaRecetasBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializamos el RecyclerView.
        RecyclerView recyclerView = view.findViewById(R.id.recycler_recetas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext())); // Diseño en lista vertical.

        // Obtenemos una instancia del ViewModel.
        recetasViewModel = new ViewModelProvider(this).get(RecetasViewModel.class);

        // Configuramos el adaptador del RecyclerView.
        adaptador = new RecetasAdapter();
        recyclerView.setAdapter(adaptador);

        // Observamos los datos de la lista de películas.
        recetasViewModel.obtenerTodasLasRecetas().observe(getViewLifecycleOwner(), recetas -> {
            // Actualizamos los datos del adaptador cuando cambien en el ViewModel.
            adaptador.establecerRecetas(recetas);
        });
    }
}