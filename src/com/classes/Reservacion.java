/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.classes;
/*
 * @author Nelson Flamenco
*/
public class Reservacion {
    static int reservKey = 1;
    private int codReservacion;
    private int numCliente;
    private int dias;
    private int numPersonas;
    private String fechahospedaje;
    private double totalReserva;
    
    public Reservacion(int codReservacion,int numCliente,int dias,int numPersonas,String fechahospedaje) {
        this.codReservacion=codReservacion;
        this.numCliente=numCliente;
        this.dias=dias;
        this.numPersonas=numPersonas;
        this.fechahospedaje=fechahospedaje;
    }
    
    public Reservacion(){}

    public String getFechahospedaje() {
        return fechahospedaje;
    }

    public void setFechahospedaje(String fechahospedaje) {
        this.fechahospedaje = fechahospedaje;
    }

    public double getTotalReserva() {
        return totalReserva;
    }

    public void setTotalReserva(double totalReserva) {
        this.totalReserva = totalReserva;
    }
    
    
    
    public int getCodReservacion() {
        return codReservacion;
    }
    
    public void setCodReservacion(int codReservacion) {
        this.codReservacion = codReservacion;
    }
    
    public int getNumCliente() {
        return numCliente;
    }
    
    public void setNumCliente(int numCliente) {
        this.numCliente = numCliente;
    }
    
    public int getDias() {
        return dias;
    }
    
    public void setDias(int dias) {
        this.dias = dias;
    }
    
    public int getNumPersonas() {
        return numPersonas;
    }
    
    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }
    
    public String getFechaHospedaje() {
        return fechahospedaje;
    }
    
    public void setFechaHospedaje(String fechahospedaje) {
        this.fechahospedaje = fechahospedaje;
    }
}
