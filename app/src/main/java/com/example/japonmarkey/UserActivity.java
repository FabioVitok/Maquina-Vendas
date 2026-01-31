package com.example.japonmarkey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.japonmarkey.models.Produto;
import com.example.japonmarkey.models.Utilizador;

public class UserActivity extends AppCompatActivity {


    // Decalar as variaveis de instancia
    private TextView textUsername;
    private TextView textSaldo;
    private Utilizador user1;



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

        // Inicialização das Views
        ImageButton backButton = findViewById(R.id.backButton);
        Button buttonLogOut = findViewById(R.id.buttonLogOut);
        Button buttonCarregarSaldo = findViewById(R.id.buttonCarregarSaldo);
        this.textUsername = findViewById(R.id.textUsername);
        this.textSaldo = findViewById(R.id.textSaldo);

        // Chamar o metodo para receber os dados do utilizador vindos do MainActivity
        receberDadosUsuario();

        // Chamar o metodo para atualizar as infromações do utilizador
        atualizarInformacoes();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passSaldo();
            }
        });

        // setOnClickListener do botão de LogOut
        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Volta a entranceActivty
                Intent intent = new Intent(UserActivity.this, EntranceActivity.class);
                startActivity(intent);
            }
        });

        // setOnClickListener do botão de carregar saldo
        buttonCarregarSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chama o metodo para realizar o carregamento pelo bottomsheet
                RealizarCarregamento();
            }
        });
    }

    // Metodo para retornar o saldo ao mainactivity
    private void passSaldo() {
            Intent returnIntent = new Intent();

            // Verifica se há dados do utilizador para passar antes de os enviar
            if (user1 != null) {
                double saldoAtual = user1.getSaldo();
                // Adiciona o saldo atual aos dados a serem enviados
                returnIntent.putExtra("NOVO_SALDO", saldoAtual);
            }
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
        }


    // Metodo que recebe o user vindo do MainActivity
    private void receberDadosUsuario() {
        Intent intent = getIntent();
        user1 = (Utilizador) intent.getSerializableExtra("USER_OBJECT");
    }


    // Metodo para atualizar informações do utilizador
    private void atualizarInformacoes() {
        textUsername.setText("Nome: " + user1.getUsername());
        // Formatar saldo com vírgula
        String saldoFormatado = String.format("Saldo: €%.2f", user1.getSaldo())
                .replace(".", ",");
        textSaldo.setText(saldoFormatado);
    }

    public void RealizarCarregamento() {

        // Cria a janela para o BottomSheet
        SaldoBottomSheet bottomSheet = new SaldoBottomSheet();

        // Cria um listener para quando o utilizador escolher a quantia a carregar
        bottomSheet.setOnCarregamentoListener(new SaldoBottomSheet.OnCarregamentoListener() {
            @Override
            public void onCarregamentoRealizado(double carregamento) {

              //Carrega o saldo com a quantia escolhida no bottomsheet
                user1.carregarSaldo(carregamento);
                // Atualiza as informações do utilizador (Necessário pela mudança no saldo)
                atualizarInformacoes();
            }
        });
        // Abre o BottomSheet
        bottomSheet.show(getSupportFragmentManager(), "SaldoBottomSheet");
    }
}