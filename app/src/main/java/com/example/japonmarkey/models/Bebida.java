package com.example.japonmarkey.models;

public class Bebida extends Produto
{
    private final boolean energetico;
    private static final String TIPO = "Bebida";

    //Construtor da class
    public Bebida(String nome, double preco, int stock, String imagem, boolean energetico)
    {
        super(nome, TIPO, preco, stock, imagem);
        this.energetico = energetico;
    }

    //Metodo para exibir as infromaçoes do produto incluindo as informaçoes adicionas das bebidas
    @Override
    public String exibirInfo()
    {

        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(this.getNome()).append("\n");
        sb.append("Tipo: ").append(this.TIPO).append("\n");
        sb.append("Preço: ").append(this.getPreco()).append("\n");
        sb.append("Stock: ").append(this.getStock()).append("\n");
        if(energetico)
            sb.append("Energético").append("\n");
        else
        {
            sb.append("Não é energético").append("\n");
        }
        return sb.toString();

    }
}


