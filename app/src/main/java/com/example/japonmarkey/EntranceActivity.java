package com.example.japonmarkey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EntranceActivity extends AppCompatActivity {

    // Definir uma var. de istância do tipo button
    // Vai servir para manipular o Button

    Button buttonToSignUp;
    Button buttonToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_entrance);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Aqui vamos colocar o eventlistener no btn login
        // 1. Apanhar o componente no xml
        this.buttonToSignUp = findViewById(R.id.buttonToSignUp);
        // Aplicar o eventlistner
        this.buttonToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(LoginActivity.this, "Cliquei no btn Login", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EntranceActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
        // Aqui vamos colocar o eventlistener no btn login
        // 1. Apanhar o componente no xml
        this.buttonToSignUp = findViewById(R.id.buttonToSignUp);
        // Aplicar o eventlistner
        this.buttonToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(LoginActivity.this, "Cliquei no btn Login", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EntranceActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
        // Aqui vamos colocar o eventlistener no btn login
        // 1. Apanhar o componente no xml
        this.buttonToLogin= findViewById(R.id.buttonToLogin);
        // Aplicar o eventlistner
        this.buttonToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(LoginActivity.this, "Cliquei no btn Login", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EntranceActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}