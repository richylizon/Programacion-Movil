package com.loopwiki.loginregisterwithsqlite.peso_madre.Entidades;

public class Peso {

    private double pesoInicial;
    private double pesoActual;

    public Peso(double pesoInicial, double pesoActual) {
        this.pesoInicial = pesoInicial;
        this.pesoActual = pesoActual;
    }

    public double getPesoInicial() {
        return pesoInicial;
    }

    public void setPesoInicial(double pesoInicial) {
        this.pesoInicial = pesoInicial;
    }

    public double getPesoActual() {
        return pesoActual;
    }

    public void setPesoActual(double pesoActual) {
        this.pesoActual = pesoActual;
    }
}
