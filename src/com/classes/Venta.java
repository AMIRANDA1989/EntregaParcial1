/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.classes;
/*
 * @author Nelson Flamenco
*/
public class Venta {
    static int ventaKey = 1;
    private int codVenta;
    private int codReservacion;
    private String fecha;
    private double total;
    
    public Venta(int codVenta,int codReservacion,String fecha,double total){
        this.codVenta=codVenta;
        this.codReservacion=codReservacion;
        this.fecha=fecha;
        this.total=total;
    }
    
    public Venta(){}
    
    public int getCodVenta() {
        return codVenta;
    }
    
    public int getCodReservacion() {
        return codReservacion;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public double getTotal() {
        return total;
    }
    
    public void setCodVenta(int codVenta) {
        this.codVenta = codVenta;
    }
    
    public void setCodReservacion(int codReservacion) {
        this.codReservacion = codReservacion;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public void setTotal(double total) {
        this.total = total;
    }
}
