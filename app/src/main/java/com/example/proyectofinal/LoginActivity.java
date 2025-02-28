package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.proyectofinal.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * LoginActivity maneja el inicio de sesión con Firebase Authentication.
 */
public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inicializamos View Binding
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Inicializar Firebase
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        // Click en "Iniciar Sesión"
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logarUsuario();
            }
        });

        // Click en "Registrarse" para ir a la actividad de registro
        binding.btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Inicia sesión con un usuario existente.
     */
    private void logarUsuario() {
        String email = binding.textoMail.getText().toString().trim();
        String password = binding.textoPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Usuario existente
                            FirebaseUser usuario = mAuth.getCurrentUser();
                            if (usuario != null) {
                                Toast.makeText(LoginActivity.this,
                                        "Bienvenido de nuevo: " + usuario.getEmail(),
                                        Toast.LENGTH_SHORT).show();
                            }
                            // Pasamos a la actividad principal
                            iniciarMainActivity();
                        } else {
                            // Error al iniciar sesión
                            Toast.makeText(LoginActivity.this,
                                    "Error al iniciar sesión: " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * Navega a la MainActivity
     */
    private void iniciarMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}