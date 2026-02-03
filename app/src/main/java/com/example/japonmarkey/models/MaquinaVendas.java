package com.example.japonmarkey.models;

import android.content.Context;

import com.example.japonmarkey.R;

import java.util.ArrayList;

public class MaquinaVendas
{
    private String localizacao;
    private Utilizador user;
    private ArrayList<Produto> produtos = new ArrayList<Produto>();

    //Construtor da class
    public MaquinaVendas(String localizacao, Utilizador user, ArrayList<Produto> produtos)
    {
        this.localizacao = localizacao;
        this.user = user;
        this.produtos = produtos;
    }

    public String getLocalizacao()
    {
        return this.localizacao;
    }

    public Utilizador getUtilizador()
    {
        return this.user;
    }

    public ArrayList<Produto> getProdutos()
    {
        return produtos;
    }

    //Metodo Para comprar um produto
    public String comprar(Produto p1, int quantidade, Context context)
    {
        double precoTotal = p1.getPreco() * quantidade;

        //Verifica se o stock é sufeciente e se o utilizador tem saldo sufeciente
        if(p1.verificarStock(quantidade) && this.user.verificarSaldo(precoTotal))
        {
            p1.reduzirStock(quantidade);
            this.user.descontarSaldo(precoTotal);
            return context.getString(R.string.compraSucesso);
        }
        else if(p1.verificarStock(quantidade) && !this.user.verificarSaldo(precoTotal))
            return context.getString(R.string.noSaldo);
        else if(!p1.verificarStock(quantidade) && this.user.verificarSaldo(precoTotal))
            return context.getString(R.string.noStock);
        else
            return context.getString(R.string.noStock);
    }
}

