package com.example.japonmarkey;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CompraBottomSheet extends BottomSheetDialogFragment {


    // Interface para comunicação entre o BottomSheet e o MainActivity
    public interface OnCompraListener {

        // metodo chamado quando o utilizador confirma uma compra.
        void onCompraRealizada(int quantidade);
    }
    private int stockDisponivel;
    private OnCompraListener compraListener;

    // Quantidade atual selecionada pelo utilizador (default 0)
    private int quantidade = 1;

    // Guarda quem vai receber os avisos de compra
    public void setOnCompraListener(OnCompraListener listener) {
        this.compraListener = listener;
    }

    // Metodo para criar um bottomsheet com dados do produto
    public static CompraBottomSheet newInstance(String nomeProduto, double precoProduto, int stockProduto) {

        // Cria uma nova instância do BottomSheet
        CompraBottomSheet fragment = new CompraBottomSheet();

        // Cria um Bundle
        Bundle bundle = new Bundle();

        // Coloca os dados no Bundle
        bundle.putString("NOME_PRODUTO", nomeProduto);
        bundle.putDouble("PRECO_PRODUTO", precoProduto);
        bundle.putInt("STOCK_PRODUTO", stockProduto);

        // Anexa o Bundle ao Fragment
        fragment.setArguments(bundle);

        // Retorna o bottomSheet com os dados do produto
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Associa a classe ao respetivo layout
        View view = inflater.inflate(R.layout.bottom_sheet_compra, container, false);

        // Verifica se os foram passados dados no bundle
        if (getArguments() != null) {

            //Atribui os dados do budnle a variaveis locais
            String nome = getArguments().getString("NOME_PRODUTO");
            double preco = getArguments().getDouble("PRECO_PRODUTO");
            int stock = getArguments().getInt("STOCK_PRODUTO");
            this.stockDisponivel = stock;

            // Inicialização das Views
            TextView textNomeProduto = view.findViewById(R.id.textNomeProduto);
            TextView textPrecoProduto = view.findViewById(R.id.textPrecoProduto);
            TextView textStockProduto = view.findViewById(R.id.textStockProduto);

            // Mostra os dados recebidos do produto nos textViews
            textNomeProduto.setText("Produto: " + nome);
            textPrecoProduto.setText(String.format("Preço: €%.2f", preco));
            textStockProduto.setText("Stock: " + stock);

        }
            // Associar as variaveis locais aos respetivos elementos do xml
            TextView textQuantidade = view.findViewById(R.id.textQuantidade);
            Button btnMenos = view.findViewById(R.id.btnMenos);
            Button btnMais = view.findViewById(R.id.btnMais);
            Button btnComprar = view.findViewById(R.id.btnComprar);
            Button btnCancelar = view.findViewById(R.id.btnCancelar);

            // setOnClickListener do botão menos
            btnMenos.setOnClickListener(v -> {
                // Verifica se a quantidade é maior que 1 (quantidade não pode ser 0 ou negativo)
                if (quantidade > 1) {
                    // Diminui a quantidade
                    quantidade--;
                    // Atualiza a o textview da quantidade
                    textQuantidade.setText(String.valueOf(quantidade));
                }
            });

            // setOnClickListener do botão mais
            btnMais.setOnClickListener(v -> {
                // Verifica se a quantidade é menor que o stock disponivel (quantidade não pode ser maior que o stock)
                if(quantidade < stockDisponivel) {
                    // Aumenta a quantidade
                    quantidade++;
                    // Atualiza a o textview da quantidade
                    textQuantidade.setText(String.valueOf(quantidade));
                }
            });

            // setOnclickListener para o botão comprar
            btnComprar.setOnClickListener(v -> {
                // Notifica o listener sobre a compra realizada
                if (compraListener != null) {
                    compraListener.onCompraRealizada(quantidade);
                }
                dismiss(); // Fecha o BottomSheet
            });


            // setOnclickListener para o botão cancelar
            btnCancelar.setOnClickListener(v -> {
                dismiss(); // Fecha o BottomSheet
            });

            return view;
        }
    }
