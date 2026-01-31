package com.example.japonmarkey.models;

import java.io.Serializable;

public class Utilizador implements Serializable {
    public String username;
    public String email;
    public String password;
    public double saldo;

    //Construtor da class
    public Utilizador(String username, String email, String password) {
        this.username = username;

        this.email = email;

        this.password = password;

        this.saldo = 0;
    }

    //Métodos get e set para os atributos da class
    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    //Metodo para Carregar saldo na conta do utilizador
    public boolean carregarSaldo(double carregamento) {
        //Verifica se o carregamento é maior que 0
        if (carregamento > 0) {
            this.setSaldo(this.getSaldo() + carregamento);
            //System.out.println("Carregamento efetuado com sucesso! O seu saldo atual é " + this.getSaldo() + "€.");
            return true;
        } else {
            //System.out.println("Não foi possivel efetuar o carregamento, o montante do carregamento é negativo ou nulo.");
            return false;
        }
    }

    public boolean verificarSaldo(double desconto)
    {
        //Verifica se o desconto é menor ou igual ao saldo na conta do utilizador
        if(desconto <= this.getSaldo()){
            return true;
        }
        return false;
    }

    public boolean descontarSaldo(double desconto)
    {
        this.setSaldo(this.getSaldo() - desconto);
        return true;
    }
}