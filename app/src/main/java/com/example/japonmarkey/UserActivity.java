package com.example.japonmarkey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserActivity extends AppCompatActivity {


    private TextView textUsername;
    private TextView textSaldo;

    private String nomeUsuario;
    private double saldoAtual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton backButton = findViewById(R.id.backButton);
        Button buttonLogOut = findViewById(R.id.buttonLogOut);
        this.textUsername = findViewById(R.id.textUsername);
        this.textSaldo = findViewById(R.id.textSaldo);

        // 2. Receber dados do MainActivity
        receberDadosUsuario();

        // 3. Atualizar textos
        atualizarInformacoes();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(LoginActivity.this, "Cliquei no btn Login", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UserActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(LoginActivity.this, "Cliquei no btn Login", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UserActivity.this, EntranceActivity.class);
                startActivity(intent);
            }
        });
    }
    private void receberDadosUsuario() {
        Intent intent = getIntent();
        nomeUsuario = intent.getStringExtra("NOME");
        saldoAtual = intent.getDoubleExtra("SALDO", 0.0);
    }

    private void atualizarInformacoes() {
        textUsername.setText("Nome: " + nomeUsuario);
        // Formatar saldo com vírgula
        String saldoFormatado = String.format("Saldo: €%.2f", saldoAtual)
                .replace(".", ",");
        textSaldo.setText(saldoFormatado);
    }
}