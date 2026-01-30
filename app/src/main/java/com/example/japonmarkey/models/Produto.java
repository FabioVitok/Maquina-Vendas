package com.example.japonmarkey.models;

public abstract class Produto
{
    private final String nome;
    private double preco;
    private int stock;
    private String imagem;
    private String tipo;

    //Construtor da class abstract Produto
    public Produto(String nome, String tipo, double preco, int stock, String imagem)
    {
        this.nome = nome;

        this.tipo = tipo;

        this.preco = preco;

        this.stock = stock;

        this.imagem = imagem;
    }

    //Metodos Get e Set para os atributos do Produto
    public String getNome()
    {
        return this.nome;
    }

    public String getTipo()
    {
        return this.tipo;
    }

    public double getPreco()
    {
        return this.preco;
    }

    public int getStock()
    {
        return this.stock;
    }

    public void setStock(int stock)
    {
        this.stock = stock;
    }

    public String getImagem()
    {
        return this.imagem;
    }

    public boolean verificarStock(int quantidade)
    {
        //Verifica se o stock não é menor do que a quantidade que se deseja retirar
        if(this.getStock() >= quantidade)
        {
            return true;
        }
        else
            return false;

    }
    //Metodo para reduzir o stock de um protudo
    public boolean reduzirStock(int quantidade)
    {
        this.stock = this.getStock() - quantidade;
        return true;
    }

    //Metodo para repor Stock de um produto
    public boolean reStock(int novoStock)
    {
        this.stock = this.getStock() + novoStock;
        //System.out.println("O Stock do produto foi atualizado");
        return true;
    }

    //metodo para exibir as infromações de um produto
    public String exibirInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(this.getNome()).append("\n");
        sb.append("Tipo: ").append(this.getTipo()).append("\n");
        sb.append("Preço: ").append(this.getPreco()).append("\n");
        sb.append("Stock: ").append(this.getStock()).append("\n");
        return sb.toString();

    }
}