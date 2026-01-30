package com.example.japonmarkey.models;

public class Doce extends Produto
{
    private boolean frio;
    private static final String TIPO = "Doce";

    //Construtor da class
    public Doce(String nome, double preco, int stock, String imagem,boolean frio)
    {
        super(nome, TIPO, preco, stock, imagem);
        this.frio = frio;
    }

    //Metodo para exibir as infromaçoes do produto incluindo as informaçoes adicionas dos doces
    @Override
    public String exibirInfo()
    {

        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(this.getNome()).append("\n");
        sb.append("Tipo: ").append(this.TIPO).append("\n");
        sb.append("Preço: ").append(this.getPreco()).append("\n");
        sb.append("Stock: ").append(this.getStock()).append("\n");
        if(frio == true)
            sb.append("Colocar no congelador").append("\n");
        return sb.toString();

    }
}
