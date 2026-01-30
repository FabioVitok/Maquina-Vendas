package com.example.japonmarkey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.japonmarkey.models.Bebida;
import com.example.japonmarkey.models.Doce;
import com.example.japonmarkey.models.ImageHelper;
import com.example.japonmarkey.models.MaquinaVendas;
import com.example.japonmarkey.models.Produto;
import com.example.japonmarkey.models.Snack;
import com.example.japonmarkey.models.Utilizador;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Declaração das Variaveis de instância para a classe
    private int produtoAtualIndex = 0;
    private MaquinaVendas mq1;
    private final int primeiroElemento = 0;




    // Metodo onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Chama o metodo InicializarMaquina que cria o user, a maquina e adiciona todos os produtos
        IncializarMaquina();
        // Chama o metodo MostrarProduto que mostra as infromações do produto no ecrã
        MostrarProdutos();

        // Associar as variaveis locais aos respetivos elementos dos xml
        ImageButton userButton = findViewById(R.id.userButton);
        ImageButton imageButtonBuy = findViewById(R.id.imageButtonBuy);
        ImageButton imageButtonBackward = findViewById(R.id.imageButtonBackward);
        ImageButton imageButtonForward = findViewById(R.id.imageButtonForward);

        // setOnClickListener do botão user que troca de activity do main para a user
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
                // Chama o metodo PassUserInfo para passar as informações do user para a UserActivity
                PassUserInfo();
            }
        });

        // setOnClickListener do botão comprar
        imageButtonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chama o metodo RealizarCompra para comprar o item presente no ecrã
                RealizarCompra();
            }
        });

        // setOnClickListener do botão anterior
        imageButtonBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Criar a variavel local ultimoProduto que equivale ao index do ultimo produto do arrayList
                int ultimoProduto = mq1.produtos.size()-1;

                // Verifica se o produtoAtualIndex está no primeiro elemento do arrayList
                if(produtoAtualIndex == primeiroElemento){
                    // Se estiver, em vez de andar para traz ele vai ser levado para o fim do arrayList
                    produtoAtualIndex = ultimoProduto;
                }
                else{
                    // Se ele não estiver, ele anda um produto para tras
                    produtoAtualIndex= produtoAtualIndex-1;
                }
                // Atualiza o Produto que está a ser Mostrado
                MostrarProdutos();
                }


        });

        // setOnClickListener do botão Proximo
        imageButtonForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Criar a variavel local ultimoProduto que equivale ao index do ultimo produto do arrayList
                int ultimoProduto = mq1.produtos.size()-1;

                // Verifica se o produtoAtualIndex está no ultimo elemento o arrayList
                if(produtoAtualIndex == ultimoProduto){
                    // Se estiver, em vez de andar prà frente ele vai ser levado para o inicio do arrayList
                    produtoAtualIndex = primeiroElemento;
                }
                else {
                    // Se ele não estiver, ele anda um produto prà frente
                    produtoAtualIndex = produtoAtualIndex + 1;
                }
                // Atualiza o Produto que está a ser Mostrado
                MostrarProdutos();
            }
        });
    }
    // O OnCreat é fechado


    // Criação dos métodos Utilizados nesta Class
    public void RealizarCompra(){

            // Cria a janela para o BottomSheet
            CompraBottomSheet bottomSheet = new CompraBottomSheet();

        //Cria a variavel local produto que equivale ao produto atualmente no ecrã
        Produto produto = mq1.produtos.get(produtoAtualIndex);

        // MUDANÇA AQUI: Use newInstance em vez de new CompraBottomSheet()
        bottomSheet = CompraBottomSheet.newInstance(
                produto.getNome(),    // Passa o nome
                produto.getPreco(),  // Passa o preço
                produto.getStock()    // Passa o Stock
        );

            // Cria um listener para quando o utilizador escolher uma quantidade
            bottomSheet.setOnCompraListener(new CompraBottomSheet.OnCompraListener() {
                @Override
                public void onCompraRealizada(int quantidadeRecebida) {
                    // Cria a String de resultado da compra enquanto realiza a compra do produto mostrado atualmente no ecrã e com a quantidade recebida do bottomsheet
                    String resultado = mq1.comprar(mq1.produtos.get(produtoAtualIndex),quantidadeRecebida, MainActivity.this);
                    // Cria um Toast que mostra a string do resultado da compra
                    Toast.makeText(MainActivity.this, resultado, Toast.LENGTH_SHORT).show();
                    // Atualiza as informações do produto (Necessário pela mudança no stock)
                    MostrarProdutos();
                }
            });
            // Abre o BottomSheet
            bottomSheet.show(getSupportFragmentManager(), "CompraBottomSheet");
    }


        // Metodo Para inicializar a Maquina de vendas
        public void IncializarMaquina() {
        // Cria o utilizador
        Utilizador user1 = new Utilizador("Fábio", "fabio.vitoriano@icloud.com", "passwow");
        user1.carregarSaldo(100.50);
        // Cria o arraylist de produtos disponiveis
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        // Cria a maquina de vendas e associa o utilizador e o arraylist
        this.mq1 = new MaquinaVendas("Loures", user1, produtos);

        // Adiciona cada produto á maquina de vendas
        Doce dc1 = new Doce("Mochi", 3.60, 6, "mochi_drawable", true);
        this.mq1.produtos.add(dc1);
        Snack sn1 = new Snack("Noodles", 4.60, 4, "C:/Users/Pictures/noodles", false);
        this.mq1.produtos.add(sn1);
        Bebida bb1 = new Bebida("Monster Morango", 1.60, 3, "C:/Users/Pictures/monster_morango", true);
        this.mq1.produtos.add(bb1);
    }

    // Metodo Para Mostrar as informações do produto no ecrã
    public void MostrarProdutos() {
        // Cria a variavel local produto que equivale ao produto atualmente no ecrã
        Produto produto = mq1.produtos.get(produtoAtualIndex);


        // Associa a variavel da instancia ao respetivo elemento no xml
        TextView nomeProduto = findViewById(R.id.nomeProduto);
        // Atribui o nome do produto no index atual ao textview nomeProduto
        nomeProduto.setText("" + produto.getNome());


        // Associa a variavel da instancia ao respetivo elemento no xml
        TextView precoProduto = findViewById(R.id.precoProduto);

        // Cria uma string com o formato certo para apresentar o preço da forma desejada. EX: 4,50€
        String precoFormatado = String.format("%.2f€", produto.getPreco());

        // Atribui essa String ao Textview precoProduto
        precoProduto.setText(precoFormatado);


        // Associa a variavel da instancia ao respetivo elemento no xml
        TextView stockProduto = findViewById(R.id.stockProduto);
        // Atribui o stock do produto no index atual ao textview stockProduto
        stockProduto.setText("Stock: " + produto.getStock());

        // Associa a variavel da instancia ao respetivo elemento no xml
        ImageView imagemProduto = findViewById(R.id.imagemProduto);
        // Obtem o id da imagem pelo nome
        int imagemId = ImageHelper.getDrawableResourceId(
                this,
                produto.getImagem()
        );


        if (imagemId != 0) {
            imagemProduto.setImageResource(imagemId);
        } else {
            // Imagem padrão caso não encontre a imagem do produto atual
            imagemProduto.setImageResource(R.drawable.japon_logo);
        }
    }

    // Metodo para passar a informação do user para a userActivity
    private void PassUserInfo() {
        Intent intent = new Intent(MainActivity.this, UserActivity.class);

        // Verifica se há dados do utilizador para passar antes de os enviar
        if (mq1 != null && mq1.user != null) {
            // Adiciona o nome do utilizador e o saldo aos dados a serem enviados
            intent.putExtra("NOME", this.mq1.user.getUsername());
            intent.putExtra("SALDO", this.mq1.user.getSaldo());
        }

        // Inicia a UserActivity e leva as infromaçoes do user no intent
        startActivity(intent);
    }

}
