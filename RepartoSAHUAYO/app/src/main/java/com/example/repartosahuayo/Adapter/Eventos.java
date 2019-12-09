package com.example.repartosahuayo.Adapter;

public class Eventos {


    public String getEvento() {
        return evento;
    }

    public String getFolio() {
        return folio;
    }

    public double getImporte() {
        return importe;
    }

    private String evento;
    private String folio;
    private double importe;
    public Eventos(String evento, String folio, double importe){
        this.evento=evento;
        this.folio=folio;
        this.importe=importe;
    }
}
