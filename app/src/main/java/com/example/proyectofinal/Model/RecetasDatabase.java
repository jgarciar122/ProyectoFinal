package com.example.proyectofinal.Model;

import androidx.databinding.adapters.Converters;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import android.content.Context;

@Database(entities = {Receta.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)  // Línea para Converters
public abstract class RecetasDatabase extends RoomDatabase {
    public abstract RecetaDAO recetaDAO();

    private static volatile RecetasDatabase instancia;

    public static RecetasDatabase obtenerInstancia(final Context context) {
        if (instancia == null) {
            synchronized (RecetasDatabase.class) {
                if (instancia == null) {
                    instancia = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    RecetasDatabase.class,
                                    "base_datos_recetas" //
                            )
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instancia;
    }
}
