/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.classes;
/*
 *
 * @author allan
*/
public class Habitacion {
    private String codHabitacion; //Codigo de la habitacion
    private boolean habilitado; //Indicador si esta habilitada la habitacion
    private int capacidad;  //Capacidad de clientes de la habitacion, puede ser una o dos personas OJO:Las habitaciones con numero par deben ser dobles
    private boolean disponible; //Indica si se puede utilizar la habitacion en caso de reserva o venta
    private double precio;

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio, int numpiso) {
        this.precio = precio * (1+(0.10*numpiso));
    }

    public Habitacion(String codHabitacion,boolean habilitado,int capacidad,boolean disponible,double precio) {
        this.codHabitacion=codHabitacion;
        this.habilitado=habilitado;
        this.capacidad=capacidad;
        this.disponible=disponible;
        this.precio=precio;
    }
    
    public Habitacion() {}

    public String getCodHabitacion() {
        return codHabitacion;
    }

    public void setCodHabitacion(String codHabitacion) {
        this.codHabitacion = codHabitacion;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
