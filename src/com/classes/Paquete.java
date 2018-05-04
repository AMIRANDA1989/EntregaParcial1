/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.classes;
/*
 * @author Nelson Flamenco
*/
public class Paquete {
    int codPaquete;
    private String nombre;
    private String descripcion;
    private double precio;
    
    public Paquete(int codPaquete,String nombre,String descripcion,double precio){
        this.codPaquete=codPaquete;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.precio=precio;
    }
            
    public Paquete(){}
    
    public int getCodPaquete() {
        return codPaquete;
    }
    
    public void setCodPaquete(int codPaquete) {
        this.codPaquete = codPaquete;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public double getPrecio() {
        return precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
