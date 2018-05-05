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
public class Servicio {
    static int servKey = 1;
    private int codServicio;
    private String nombre;

    public Servicio() {
    }

    public Servicio(int codServicio, String nombre) {
        this.codServicio = codServicio;
        this.nombre = nombre;
    }

    public static int getServKey() {
        return servKey;
    }

    public static void setServKey(int servKey) {
        Servicio.servKey = servKey;
    }

    public int getCodServicio() {
        return codServicio;
    }

    public void setCodServicio(int codServicio) {
        this.codServicio = codServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
