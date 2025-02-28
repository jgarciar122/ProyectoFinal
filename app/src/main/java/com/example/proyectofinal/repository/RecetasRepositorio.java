package com.example.proyectofinal.repository;

import androidx.annotation.NonNull;

import com.example.proyectofinal.Model.Receta;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RecetasRepositorio {

    public interface AuthCallback {
        void onSuccess();
        void onFailure(String error);
    }

    public interface RecetasCallback {
        void onSuccess(List<Receta> lista);
        void onFailure(String error);
    }

    private final FirebaseAuth mAuth;
    private final FirebaseFirestore db;
    private final CollectionReference recetasRef;

    public RecetasRepositorio() {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        recetasRef = db.collection("recetas"); // Referencia a la colección de Firestore
    }

    public FirebaseUser getUsuarioActual() {
        return mAuth.getCurrentUser();
    }

    // Publicar una receta
    public void publicarReceta(String nombre, String descripcion, List<String> ingredientes, List<String> pasos, float valoracion, byte[] imagen, List<String> comentarios, AuthCallback callback) {
        FirebaseUser user = getUsuarioActual();
        if (user == null) {
            callback.onFailure("No hay usuario autenticado");
            return;
        }

        // Generar un ID automático en Firestore
        DocumentReference newRecetaRef = recetasRef.document();

        Receta receta = new Receta(newRecetaRef.getId(), nombre, descripcion, ingredientes, pasos, valoracion, imagen, comentarios, user.getEmail());

        newRecetaRef.set(receta)
                .addOnSuccessListener(aVoid -> callback.onSuccess())
                .addOnFailureListener(e -> callback.onFailure(e.getMessage()));
    }

    // Leer todas las recetas ordenadas por fecha (del más reciente al más antiguo)
    public void leerRecetas(RecetasCallback callback) {
        recetasRef.orderBy("fecha", Query.Direction.DESCENDING)
                .limit(50) // Limita a las últimas 50 recetas
                .addSnapshotListener((querySnapshot, e) -> {
                    if (e != null) {
                        callback.onFailure(e.getMessage());
                        return;
                    }

                    List<Receta> lista = new ArrayList<>();
                    if (querySnapshot != null) {
                        for (DocumentSnapshot doc : querySnapshot.getDocuments()) {
                            Receta rec = doc.toObject(Receta.class);
                            if (rec != null) lista.add(rec);
                        }
                    }
                    callback.onSuccess(lista);
                });
    }

    // Eliminar una receta
    public void eliminarReceta(String id, AuthCallback callback) {
        FirebaseUser user = getUsuarioActual();
        if (user == null) {
            callback.onFailure("No hay usuario autenticado");
            return;
        }

        recetasRef.document(id).delete()
                .addOnSuccessListener(aVoid -> callback.onSuccess())
                .addOnFailureListener(e -> callback.onFailure(e.getMessage()));
    }

    // Búsqueda básica de recetas por nombre
    public void buscarRecetas(String nombre, RecetasCallback callback) {
        recetasRef.whereEqualTo("nombre", nombre)
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    List<Receta> resultados = new ArrayList<>();
                    for (DocumentSnapshot doc : querySnapshot.getDocuments()) {
                        Receta rec = doc.toObject(Receta.class);
                        if (rec != null) resultados.add(rec);
                    }
                    callback.onSuccess(resultados);
                })
                .addOnFailureListener(e -> callback.onFailure(e.getMessage()));
    }

    // Consulta más compleja: Buscar recetas de un usuario en los últimos 7 días
    public void buscarRecetasRecientesDeUsuario(String emailUsuario, RecetasCallback callback) {
        // Obtener el timestamp de hace 7 días
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        long fechaLimite = calendar.getTimeInMillis();

        // Firestore query: filtrar por usuario y fecha
        recetasRef
                .whereEqualTo("usuarioId", emailUsuario) // Buscar solo las recetas de este usuario
                .whereGreaterThan("fecha", fechaLimite) // Solo recetas de los últimos 7 días
                .orderBy("fecha", Query.Direction.DESCENDING) // Ordenar por fecha, de más reciente a más antiguo
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    List<Receta> resultados = new ArrayList<>();
                    for (DocumentSnapshot doc : querySnapshot.getDocuments()) {
                        Receta rec = doc.toObject(Receta.class);
                        if (rec != null) resultados.add(rec);
                    }
                    callback.onSuccess(resultados);
                })
                .addOnFailureListener(e -> callback.onFailure(e.getMessage()));
    }
}