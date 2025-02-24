package com.example.proyectofinal.UI;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.proyectofinal.Model.Receta;
import com.example.proyectofinal.databinding.ItemRecetasBinding;

import java.util.ArrayList;
import java.util.List;

// Adaptador para gestionar la lista de recetas en el RecyclerView.
public class RecetasAdapter extends RecyclerView.Adapter<RecetasAdapter.RecetasViewHolder> {

    private List<Receta> listaRecetas = new ArrayList<>(); // Lista de recetas.

    @NonNull
    @Override
    public RecetasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflamos el layout de la tarjeta (item_receta.xml).
        ItemRecetasBinding binding = ItemRecetasBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecetasViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecetasViewHolder holder, int position) {
        // Obtenemos la receta en la posición actual.
        Receta recetaActual = listaRecetas.get(position);

        // Configuramos los datos en las vistas.
        holder.binding.textViewNombre.setText(recetaActual.getNombre());
        holder.binding.textViewDescripcion.setText(recetaActual.getDescripcion());
        holder.binding.textViewUsuario.setText(recetaActual.getUsuarioId());
        holder.binding.barraValoracion.setRating(recetaActual.getValoracion());
    }

    @Override
    public int getItemCount() {
        // Devuelve el número de elementos en la lista.
        return listaRecetas.size();
    }

    // Método para actualizar la lista de recetas.
    public void establecerRecetas(List<Receta> recetas) {
        this.listaRecetas = recetas;
        notifyDataSetChanged(); // Notifica al adaptador que los datos han cambiado.
    }

    // Clase interna ViewHolder para vincular las vistas de cada tarjeta.
    static class RecetasViewHolder extends RecyclerView.ViewHolder {

        private final ItemRecetasBinding binding;

        public RecetasViewHolder(@NonNull ItemRecetasBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}