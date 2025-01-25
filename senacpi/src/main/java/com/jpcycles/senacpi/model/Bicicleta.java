package com.jpcycles.senacpi.model;


public class Bicicleta {
    private Integer id;
    private String modelo;
    private String marca;
    private Double precoCusto;
    private Double precoVenda;
    private Double saldo;

    public Bicicleta() {
    }

    public Bicicleta(String modelo, String marca, double precoCusto, double precoVenda, double saldo) {
        this.modelo = modelo;
        this.marca = marca;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.saldo = saldo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(Double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
}
