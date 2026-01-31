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

import com.example.japonmarkey.models.Bebida;
import com.example.japonmarkey.models.Doce;
import com.example.japonmarkey.models.MaquinaVendas;
import com.example.japonmarkey.models.Produto;
import com.example.japonmarkey.models.Snack;
import com.example.japonmarkey.models.Utilizador;

import java.util.ArrayList;

public class EntranceActivity extends AppCompatActivity {

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


        // Atribui a variavel local buttonToSignUp ao botão de ToSignUp presente no xml
        Button buttonToSignUp = findViewById(R.id.buttonToSignUp);
        // Aplica o eventlistner que faz a aplicação trocar de activity
        buttonToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(LoginActivity.this, "Cliquei no btn Login", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EntranceActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        // Atribui a variavel local buttonToLogin ao botão de ToLogin presente no xml
        Button buttonToLogin= findViewById(R.id.buttonToLogin);
        // Aplica o eventlistner que faz a aplicação trocar de activity
        buttonToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(LoginActivity.this, "Cliquei no btn Login", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EntranceActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}