package com.example.proyectofinal.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectofinal.R;
import com.example.proyectofinal.databinding.FragmentListaRecetasBinding;

public class FragmentListaRecetas extends Fragment {

    private RecetasViewModel recetasViewModel;
    private RecetasAdapter recetasAdapter;
    private FragmentListaRecetasBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListaRecetasBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_recetas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}