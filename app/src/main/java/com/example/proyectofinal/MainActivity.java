package com.example.proyectofinal;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.proyectofinal.UI.FragmentListaRecetas;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Si el fragmento aún no ha sido cargado, lo añadimos dinámicamente
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new FragmentListaRecetas())  // Usamos el ID del FragmentContainerView y el fragmento a mostrar
                    .commit();
        }
    }
}
