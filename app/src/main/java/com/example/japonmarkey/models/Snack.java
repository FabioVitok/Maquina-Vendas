package com.example.japonmarkey.models;

public class Snack extends Produto
{
    private boolean prontoComer;
    private static final String TIPO = "Snack";

    //Construtor da class
    public Snack(String nome, double preco, int stock, String imagem, boolean prontoComer)
    {
        super(nome, TIPO, preco, stock, imagem);
        this.prontoComer = prontoComer;
    }

    //Metodo para exibir as infromaçoes do produto incluindo as informaçoes adicionas dos snacks
    @Override
    public String exibirInfo()
    {

        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(this.getNome()).append("\n");
        sb.append("Tipo: ").append(this.TIPO).append("\n");
        sb.append("Preço: ").append(this.getPreco()).append("\n");
        sb.append("Stock: ").append(this.getStock()).append("\n");
        if(prontoComer)
            sb.append("Pronto a comer").append("\n");
        else
        {
            sb.append("Necessita de preparo antes de comer").append("\n");
        }
        return sb.toString();

    }
}
