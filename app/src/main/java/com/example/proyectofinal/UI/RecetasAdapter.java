package com.example.proyectofinal.UI;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.Model.Receta;
import com.example.proyectofinal.databinding.ItemRecetasBinding;

import java.util.ArrayList;
import java.util.List;

public class RecetasAdapter extends RecyclerView.Adapter<RecetasAdapter.RecetasViewHolder> {

    private List<Receta> recetas= new ArrayList<>();
    private final RecetasViewModel recetasViewModel;

    public RecetasAdapter(RecetasViewModel recetasViewModel) {
        this.recetasViewModel = recetasViewModel;
    }

    @NonNull
    @Override
    public RecetasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecetasBinding binding = ItemRecetasBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecetasViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull RecetasViewHolder holder, int position) {
        Receta recetaActual = recetas.get(position);

        holder.binding.textoNombre.setText(recetaActual.getNombre());
        holder.binding.textoIngredientes.setText(recetaActual.getIngredientes());
        holder.binding.textoPasos.setText(recetaActual.getPasos());
        holder.binding.textoValoracion.setText(recetaActual.getValoracion());
        holder.binding.textoComentarios.setText(recetaActual.getComentarios());
        holder.binding.textoUsuarioId.setText(recetaActual.getUsuarioId());
    }
}
