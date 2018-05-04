/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.classes;

/**
 *
 * @author allan
 */
public class Paquetes {

    /**
     *
     */
    public static int codPaquete = 1;
    private String nombrePaquete;
    private String descripcionServicios;
    private double precio;

    public Paquetes() {
    }

    public Paquetes(String nombrePaquete, String descripcionServicios, double precio) {
        this.nombrePaquete = nombrePaquete;
        this.descripcionServicios = descripcionServicios;
        this.precio = precio;
    }
 
    
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio){
        this.precio = precio;
    }

    
    public static int getCodPaquete() {
        return codPaquete;
    }

    public static void setCodPaquete(int codPaquete) {
        Paquetes.codPaquete = codPaquete;
    }

    public String getNombrePaquete() {
        return nombrePaquete;
    }

    public void setNombrePaquete(String nombrePaquete) {
        this.nombrePaquete = nombrePaquete;
    }

    public String getDescripcionServicios() {
        return descripcionServicios;
    }

    public void setDescripcionServicios(String descripcionServicios) {
        this.descripcionServicios = descripcionServicios;
    }
    
    
}
