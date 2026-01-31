package com.example.japonmarkey;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class SaldoBottomSheet extends BottomSheetDialogFragment {

    // Interface para comunicação entre o BottomSheet e o UserActivity
    public interface OnCarregamentoListener {

        // metodo chamado quando o utilizador confirma um carregamento.
        void onCarregamentoRealizado(double carregamento);
    }

    // Quantia do carregamento selecionada pelo utilizador (default 0)
    private double carregamento = 0;

    // Referência para quem vai receber o aviso de carregamento
    private OnCarregamentoListener carregamentoListener;

    // Guarda quem vai receber os avisos de carregamento
    public void setOnCarregamentoListener(SaldoBottomSheet.OnCarregamentoListener listener) {this.carregamentoListener = listener;}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Associa a classe ao respetivo layout
        View view = inflater.inflate(R.layout.bottom_sheet_saldo, container, false);


        // Inicialização das Views
        EditText editValor = view.findViewById(R.id.editValor);
        Button btnCarregar = view.findViewById(R.id.btnCarregar);
        Button btnCancelar = view.findViewById(R.id.btnCancelar);


        // setOnclickListener para o botão carregar
        btnCarregar.setOnClickListener(v -> {
            // Pega o valor do EditText
            String valor = editValor.getText().toString();
                    //Verifica se o EditText não está vazio
                    if (!valor.isEmpty()) {
                    // Converte a String para um valor double para o carregamento
                    carregamento = Double.parseDouble(valor);
                    // Notifica o listener sobre o carregamento e guarda o valor do mesmo
                    if (carregamentoListener != null) {
                        carregamentoListener.onCarregamentoRealizado(carregamento);
                    }
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


